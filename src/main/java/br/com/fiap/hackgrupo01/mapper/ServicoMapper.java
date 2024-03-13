package br.com.fiap.hackgrupo01.mapper;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoRequest;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoResponse;
import br.com.fiap.hackgrupo01.model.opcionais.Servico;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicoMapper {
    Servico toModel(ServicoRequest request);
    ServicoResponse toResponse(Servico servico);
    List<ServicoResponse> toResponses(List<Servico> servicos);
}
