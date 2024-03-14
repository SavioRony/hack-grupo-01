package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.ReservaMapper;
import br.com.fiap.hackgrupo01.model.dto.reserva.QuartoRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaResponseDTO;
import br.com.fiap.hackgrupo01.model.hospedagem.Quarto;
import br.com.fiap.hackgrupo01.repository.ClienteRepository;
import br.com.fiap.hackgrupo01.repository.QuartoRepository;
import br.com.fiap.hackgrupo01.repository.ReservaRepository;
import br.com.fiap.hackgrupo01.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {


    @Autowired
    protected QuartoRepository quartoRepository;

    @Autowired
    protected ReservaMapper mapper;

    @Autowired
    protected ReservaRepository repository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Override
    public ReservaResponseDTO create(ReservaRequestDTO reserva) {

        var cliente = clienteRepository.findById(reserva.getCliente().getId());

        if(cliente.isEmpty()){
            throw new NotFoundException("Cliente não cadastrado !");
        }

        validDate(reserva.getQuartos(), reserva.getEntrada(), reserva.getSaida());
        return  mapper.toResponse(repository.save(mapper.toModel(reserva)));
    }

    @Override
    public ReservaResponseDTO update(ReservaRequestDTO reserva, Long id) {

        var response = repository.findById(id);

        if(response.isEmpty()){
            throw new NotFoundException("Reserva não Encontrada !");
        }

        validDate(reserva.getQuartos(), reserva.getEntrada(), reserva.getSaida());
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

    @Override
    public List<Quarto> quartosDisponiveis(int quantidadeHospedes) {

        var allQuartos = quartoRepository.findAll();

        if(allQuartos.isEmpty()){
            return allQuartos;
        }

        return allQuartos.stream().filter(x -> x.getQuantidade() >= quantidadeHospedes).toList();
    }

    private void validDate(List<QuartoRequestDTO> quartosSelecionados, LocalDate entrada, LocalDate saida){

        StringBuilder sb = new StringBuilder();

        for(QuartoRequestDTO quarto : quartosSelecionados){

            var findQuarto = quartoRepository.findById(quarto.getId());

            findQuarto.ifPresent(value -> value.getReserva().forEach(x -> verificaConflito(sb, quarto.getId(), entrada, saida, x.getEntrada(), x.getSaida())));

            if(!sb.isEmpty()){
                throw new NotFoundException(sb.toString());
            }
        }
    }

    public static void verificaConflito(StringBuilder sb,Long id,LocalDate inicio1, LocalDate fim1, LocalDate inicio2, LocalDate fim2) {
        if (!((fim1.isBefore(inicio2) || fim1.isEqual(inicio2)) || (fim2.isBefore(inicio1) || fim2.isEqual(inicio1)))){
            sb.append("quarto de id ").append(id).append(" tem conflito de datas").append(", favor escolher outro quarto");
        }
    }
}
