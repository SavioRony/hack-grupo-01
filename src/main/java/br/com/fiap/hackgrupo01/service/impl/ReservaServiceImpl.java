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
import br.com.fiap.hackgrupo01.repository.*;
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
        ClienteDTO cliente = clienteService.findById(reserva.getCliente().getId());
        HospedagemResponse hospedagemResponse = hospedagemService.buscarHospedagemPorIdQuarto(reserva.getQuarto().getId());

        validDate(reserva.getQuarto(), reserva.getEntrada(), reserva.getSaida());
        double totalOpcionais = calcularTotalOpcionais(hospedagemResponse.getId(), reserva.getItens(), reserva.getServicos());
        Reserva model = mapper.toModel(reserva);
        model.setValorTotal(calcularValorTotal(totalOpcionais, reserva));
        Reserva save = repository.save(model);
        return  mapper.toResponse(save);
    }

    private Double calcularValorTotal(double totalOpcionais, ReservaRequestDTO reserva) {
        Quarto quarto = quartoRepository.findById(reserva.getQuarto().getId()).orElseThrow(() -> {
            throw new NotFoundException("Quarto não encontrado!");
        });
        long diasEntre = ChronoUnit.DAYS.between(reserva.getEntrada(), reserva.getSaida());
        return totalOpcionais + (quarto.getValorDiaria() * diasEntre);
    }

    private double calcularTotalOpcionais(Long idHospedagem, List<ItemReservaRequestDTO> itens, List<ServicoReservaRequest> servicos) {
        double totalOpcionais = 0;
        for(ItemReservaRequestDTO i : itens){
            Item item = itemRepository.findByIdAndHospedagemId(i.getItem().getId(), idHospedagem).orElseThrow(() -> {
                throw new NotFoundException("Item " + i.getItem().getId() + " não esta disponivel para o quarto");
            });
            totalOpcionais += (i.getQuantidade() * item.getValor());
        }

        for(ServicoReservaRequest s : servicos){
            Servico servico = servicoRepository.findByIdAndHospedagemId(s.getServico().getId(), idHospedagem).orElseThrow(() -> {
                throw new NotFoundException("Serviço " + s.getServico().getId() + " não esta disponivel para o quarto");
            });
            totalOpcionais += servico.getValor();
        }
        return totalOpcionais;
    }

    @Override
    public ReservaResponseDTO update(ReservaRequestDTO reserva, Long id) {

        var response = repository.findById(id);

        if(response.isEmpty()){
            throw new NotFoundException("Reserva não Encontrada !");
        }

        validDate(reserva.getQuarto(), reserva.getEntrada(), reserva.getSaida());
        return  mapper.toResponse(repository.save(mapper.toModel(reserva)));
    }

    @Override
    public List<ReservaResponseDTO> findAll(String email) {
        return mapper.toResponses(repository.findAll().stream().filter(x -> x.getCliente().getEmail().equalsIgnoreCase(email)).collect(Collectors.toList()));
    }

    @Override
    public void delete(Long id) {

        var response = repository.findById(id);

        if(response.isEmpty()){
            throw new NotFoundException("Reserva não Encontrada !");
        }
        repository.deleteById(id);
    }


    private void validDate(QuartoRequestDTO quartoSelecionado, LocalDate entrada, LocalDate saida){
        if(entrada.isAfter(saida)){
            throw new BadRequestException("A data de entrada não pode ser maior que a de saida");
        }
        if(entrada.equals(saida)){
            throw new BadRequestException("Não e possivel reserva a entrada e a saida para a mesma data");
        }
        boolean jaPossuiReserva = repository.existsReservaConflitante(quartoSelecionado.getId(), entrada, saida);
        if(jaPossuiReserva){
            throw new BadRequestException("Quarto ja esta reservado para data escolhida");
        }
    }
    @Override
    public List<Quarto> quartosDisponiveis(int quantidadeHospedes) {

        var allQuartos = quartoRepository.findAll();

        if(allQuartos.isEmpty()){
            return allQuartos;
        }

        return allQuartos.stream().filter(x -> x.getQuantidade() >= quantidadeHospedes).toList();
    }
}
