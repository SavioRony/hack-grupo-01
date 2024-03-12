package br.com.fiap.hackgrupo01.mapper;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.QuartoRequest;
import br.com.fiap.hackgrupo01.model.hospedagem.Quarto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuartoMapper {
    Quarto toModel(QuartoRequest quartoRequest);
}
