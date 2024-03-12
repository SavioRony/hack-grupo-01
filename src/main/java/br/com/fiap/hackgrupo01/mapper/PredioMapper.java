package br.com.fiap.hackgrupo01.mapper;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequest;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemResponse;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.PredioRequest;
import br.com.fiap.hackgrupo01.model.hospedagem.Hospedagem;
import br.com.fiap.hackgrupo01.model.hospedagem.Predio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PredioMapper {
    Predio toModel(PredioRequest predioRequest);
}
