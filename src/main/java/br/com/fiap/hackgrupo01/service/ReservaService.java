package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaRequestUpdateDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaRequestDTO;
import br.com.fiap.hackgrupo01.model.hospedagem.Quarto;

import java.time.LocalDate;
import java.util.List;

public interface ReservaService {

    ReservaResponseDTO salvarReserva(ReservaRequestDTO reserva);

    ReservaResponseDTO alterarReserva(ReservaRequestUpdateDTO reserva, Long id);

    List<ReservaResponseDTO> buscarTodasReservaDoClientePorEmail(String email);

    void deletarReserva(Long id);

    List<Quarto> buscarQuartosDisponiveis(int quantidadeHospedes, LocalDate entrada, LocalDate saida);
}
