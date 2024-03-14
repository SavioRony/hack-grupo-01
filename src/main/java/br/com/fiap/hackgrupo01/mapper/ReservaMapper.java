package br.com.fiap.hackgrupo01.mapper;

import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaRequestUpdateDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaRequestDTO;
import br.com.fiap.hackgrupo01.model.reserva.Reserva;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    Reserva toModel(ReservaRequestDTO reserva);
    Reserva toModel(ReservaRequestUpdateDTO reserva);
    ReservaResponseDTO toResponse(Reserva reserva);
    ReservaRequestDTO toRequest(ReservaRequestUpdateDTO reserva);
    List<ReservaResponseDTO> toResponses(List<Reserva> reserva);
}
