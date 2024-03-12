package br.com.fiap.hackgrupo01.mapper;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequest;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemResponse;
import br.com.fiap.hackgrupo01.model.hospedagem.Hospedagem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HospedagemMapper {
    Hospedagem toModel(HospedagemRequest hospedagemRequest);
    HospedagemResponse toResponse(Hospedagem hospedagem);
    List<HospedagemResponse> toResponses(List<Hospedagem> hospedagens);
}
