package br.com.fiap.hackgrupo01.mapper;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.PredioRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.PredioUpdateRequestDTO;
import br.com.fiap.hackgrupo01.model.hospedagem.Predio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PredioMapper {
    Predio toModel(PredioRequestDTO predioRequest);
    Predio toModel(PredioUpdateRequestDTO predioRequest);
}
