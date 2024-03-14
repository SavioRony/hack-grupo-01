package br.com.fiap.hackgrupo01.mapper;

import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaRequestDTO;
import br.com.fiap.hackgrupo01.model.reserva.Reserva;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    Reserva toModel(ReservaRequestDTO reserva);
    ReservaResponseDTO toResponse(Reserva model);
    List<ReservaResponseDTO> toResponses(List<Reserva> model);
}
