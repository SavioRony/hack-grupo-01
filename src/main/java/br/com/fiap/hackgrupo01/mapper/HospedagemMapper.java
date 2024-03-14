package br.com.fiap.hackgrupo01.mapper;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemResponseDTO;
import br.com.fiap.hackgrupo01.model.hospedagem.Hospedagem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HospedagemMapper {
    Hospedagem toModel(HospedagemRequestDTO hospedagemRequest);
    HospedagemResponseDTO toResponse(Hospedagem hospedagem);
    List<HospedagemResponseDTO> toResponses(List<Hospedagem> hospedagens);
}
