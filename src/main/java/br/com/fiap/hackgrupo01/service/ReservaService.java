package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaRequestDTO;
import br.com.fiap.hackgrupo01.model.hospedagem.Quarto;

import java.time.LocalDate;
import java.util.List;

public interface ReservaService {

    ReservaResponseDTO create(ReservaRequestDTO reserva);

    ReservaResponseDTO update(ReservaRequestDTO reserva, Long id);

    List<ReservaResponseDTO> findAll(String email);

    void delete(Long id);

    List<Quarto> quartosDisponiveis(int quantidadeHospedes);
}
