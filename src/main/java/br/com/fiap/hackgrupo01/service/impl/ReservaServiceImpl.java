package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.BadRequestException;
import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.ReservaMapper;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.*;
import br.com.fiap.hackgrupo01.model.hospedagem.Quarto;
import br.com.fiap.hackgrupo01.model.opcionais.Item;
import br.com.fiap.hackgrupo01.model.opcionais.Servico;
import br.com.fiap.hackgrupo01.model.reserva.Reserva;
import br.com.fiap.hackgrupo01.repository.ItemRepository;
import br.com.fiap.hackgrupo01.repository.QuartoRepository;
import br.com.fiap.hackgrupo01.repository.ReservaRepository;
import br.com.fiap.hackgrupo01.repository.ServicoRepository;
import br.com.fiap.hackgrupo01.service.ClienteService;
import br.com.fiap.hackgrupo01.service.HospedagemService;
import br.com.fiap.hackgrupo01.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {


    private final HospedagemService hospedagemService;

    private final QuartoRepository quartoRepository;

    private final ReservaMapper mapper;

    private final ReservaRepository repository;

    private final ClienteService clienteService;

    private final ItemRepository itemRepository;

    private final ServicoRepository servicoRepository;

    private final EmailServiceImpl emailService;


    @Override
    public ReservaResponseDTO salvarReserva(ReservaRequestDTO reserva) {
        Reserva model = validarReservaSalvar(reserva);
        Reserva save = repository.save(model);
        emailService.enviarConfirmacaoReserva(model);
        return mapper.toResponse(save);
    }

    @Override
    public ReservaResponseDTO alterarReserva(ReservaRequestUpdateDTO reserva, Long id) {
        Reserva model = validaReservaExiste(id);
        Reserva reservaValidada = validarReservaAlterar(reserva);
        reservaValidada.update(model);
        emailService.enviarConfirmacaoReserva(reservaValidada);
        return mapper.toResponse(repository.save(reservaValidada));
    }

    @Override
    public List<Quarto> buscarQuartosDisponiveis(int quantidadeHospedes, LocalDate entrada, LocalDate saida) {
        return quartoRepository.findQuartosDisponiveis(quantidadeHospedes, entrada, saida);
    }

    @Override
    public List<ReservaResponseDTO> buscarTodasReservaDoClientePorEmail(String email) {
        return mapper.toResponses(repository.findAll().stream().filter(x -> x.getCliente().getEmail().equalsIgnoreCase(email)).collect(Collectors.toList()));
    }

    @Override
    public void deletarReserva(Long id) {
        validaReservaExiste(id);
        repository.deleteById(id);
    }


    private void validDate(Quarto quartoSelecionado, LocalDate entrada, LocalDate saida) {
        if (entrada.isAfter(saida)) {
            throw new BadRequestException("A data de entrada não pode ser maior que a de saida");
        }
        if (entrada.equals(saida)) {
            throw new BadRequestException("Não e possivel reserva a entrada e a saida para a mesma data");
        }
        if (entrada.isBefore(LocalDate.now())) {
            throw new BadRequestException("A data de entrada não pode ser menor que data atual");
        }
        boolean jaPossuiReserva = repository.existsReservaConflitante(quartoSelecionado.getId(), entrada, saida, quartoSelecionado.getQuantidade());
        if (jaPossuiReserva) {
            throw new BadRequestException("Não possui quartos disponiveis para essa data.");
        }
    }

    private Reserva validarReservaSalvar(ReservaRequestDTO reserva) {
        ClienteResponseDTO cliente = clienteService.buscarClientePorId(reserva.getCliente().getId());
        Reserva validarReserva = validarReserva(reserva);
        validarReserva.getCliente().setEmail(cliente.getEmail());
        validarReserva.getCliente().setNomeCompleto(cliente.getNomeCompleto());
        return validarReserva;
    }
    private Reserva validarReservaAlterar(ReservaRequestUpdateDTO reserva) {
        return validarReserva(mapper.toRequest(reserva));
    }


    @NotNull
    private Reserva validarReserva(ReservaRequestDTO reserva) {
        Quarto quarto = quartoRepository.findById(reserva.getQuarto().getId()).orElseThrow(() -> {
            throw new NotFoundException("Quarto não encontrado!");
        });
        HospedagemResponseDTO hospedagemResponse = hospedagemService.buscarHospedagemPorIdQuarto(reserva.getQuarto().getId());
        validDate(quarto, reserva.getEntrada(), reserva.getSaida());
        double totalOpcionais = calcularTotalOpcionais(hospedagemResponse.getId(), reserva.getItens(), reserva.getServicos());
        Reserva model = mapper.toModel(reserva);
        model.setValorTotal(calcularValorTotalValidaQuantidadePessoas(totalOpcionais, reserva));
        model.setQuarto(quarto);
        return model;
    }

    private Double calcularValorTotalValidaQuantidadePessoas(double totalOpcionais, ReservaRequestDTO reserva) {
        Quarto quarto = quartoRepository.findById(reserva.getQuarto().getId()).orElseThrow(() -> {
            throw new NotFoundException("Quarto não encontrado!");
        });
        if (reserva.getQuantidadeHospedes() > quarto.getTotalPessoas()) {
            throw new BadRequestException("Quantidade de pessoas excedidas");
        }
        long diasEntre = ChronoUnit.DAYS.between(reserva.getEntrada(), reserva.getSaida());
        return totalOpcionais + (quarto.getValorDiaria() * diasEntre);
    }

    private double calcularTotalOpcionais(Long idHospedagem, List<ItemReservaRequestDTO> itens, List<ServicoReservaRequestDTO> servicos) {
        double totalOpcionais = 0;
        for (ItemReservaRequestDTO i : itens) {
            Item item = itemRepository.findByIdAndHospedagemId(i.getItem().getId(), idHospedagem).orElseThrow(() -> {
                throw new NotFoundException("Item " + i.getItem().getId() + " não esta disponivel para o quarto");
            });
            totalOpcionais += (i.getQuantidade() * item.getValor());
        }

        for (ServicoReservaRequestDTO s : servicos) {
            Servico servico = servicoRepository.findByIdAndHospedagemId(s.getServico().getId(), idHospedagem).orElseThrow(() -> {
                throw new NotFoundException("Serviço " + s.getServico().getId() + " não esta disponivel para o quarto");
            });
            totalOpcionais += servico.getValor();
        }
        return totalOpcionais;
    }

    private Reserva validaReservaExiste(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Reserva não Encontrada!");
        });
    }

}
