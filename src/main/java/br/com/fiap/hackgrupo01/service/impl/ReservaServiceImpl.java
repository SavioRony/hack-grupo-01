package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.BadRequestException;
import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.ReservaMapper;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteDTO;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemResponse;
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


    @Override
    public ReservaResponseDTO create(ReservaRequestDTO reserva) {
        return mapper.toResponse(repository.save(validarReserva(reserva)));
    }

    @Override
    public ReservaResponseDTO update(ReservaRequestDTO reserva, Long id) {
        validaReservaExiste(id);
        Reserva model = validarReserva(reserva);
        model.setId(id);
        return mapper.toResponse(repository.save(model));
    }

    @Override
    public List<Quarto> quartosDisponiveis(int quantidadeHospedes, LocalDate entrada, LocalDate saida) {
        return quartoRepository.findQuartosDisponiveis(quantidadeHospedes, entrada, saida);
    }

    @Override
    public List<ReservaResponseDTO> findAll(String email) {
        return mapper.toResponses(repository.findAll().stream().filter(x -> x.getCliente().getEmail().equalsIgnoreCase(email)).collect(Collectors.toList()));
    }

    @Override
    public void delete(Long id) {
        validaReservaExiste(id);
        repository.deleteById(id);
    }


    private void validDate(QuartoRequestDTO quartoSelecionado, LocalDate entrada, LocalDate saida) {
        if (entrada.isAfter(saida)) {
            throw new BadRequestException("A data de entrada não pode ser maior que a de saida");
        }
        if (entrada.equals(saida)) {
            throw new BadRequestException("Não e possivel reserva a entrada e a saida para a mesma data");
        }
        if (entrada.isBefore(LocalDate.now())) {
            throw new BadRequestException("A data de entrada não pode ser menor que data atual");
        }
        boolean jaPossuiReserva = repository.existsReservaConflitante(quartoSelecionado.getId(), entrada, saida);
        if (jaPossuiReserva) {
            throw new BadRequestException("Quarto ja esta reservado para data escolhida");
        }
    }

    private Reserva validarReserva(ReservaRequestDTO reserva) {
        ClienteDTO cliente = clienteService.findById(reserva.getCliente().getId());

        HospedagemResponse hospedagemResponse = hospedagemService.buscarHospedagemPorIdQuarto(reserva.getQuarto().getId());
        validDate(reserva.getQuarto(), reserva.getEntrada(), reserva.getSaida());
        double totalOpcionais = calcularTotalOpcionais(hospedagemResponse.getId(), reserva.getItens(), reserva.getServicos());
        Reserva model = mapper.toModel(reserva);
        model.setValorTotal(calcularValorTotalValidaQuantidadePessoas(totalOpcionais, reserva));
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

    private double calcularTotalOpcionais(Long idHospedagem, List<ItemReservaRequestDTO> itens, List<ServicoReservaRequest> servicos) {
        double totalOpcionais = 0;
        for (ItemReservaRequestDTO i : itens) {
            Item item = itemRepository.findByIdAndHospedagemId(i.getItem().getId(), idHospedagem).orElseThrow(() -> {
                throw new NotFoundException("Item " + i.getItem().getId() + " não esta disponivel para o quarto");
            });
            totalOpcionais += (i.getQuantidade() * item.getValor());
        }

        for (ServicoReservaRequest s : servicos) {
            Servico servico = servicoRepository.findByIdAndHospedagemId(s.getServico().getId(), idHospedagem).orElseThrow(() -> {
                throw new NotFoundException("Serviço " + s.getServico().getId() + " não esta disponivel para o quarto");
            });
            totalOpcionais += servico.getValor();
        }
        return totalOpcionais;
    }

    private void validaReservaExiste(Long id) {
        repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Reserva não Encontrada !");
        });
    }

}
