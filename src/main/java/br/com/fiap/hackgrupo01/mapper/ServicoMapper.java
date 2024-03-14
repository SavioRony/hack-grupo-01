package br.com.fiap.hackgrupo01.mapper;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoResponseDTO;
import br.com.fiap.hackgrupo01.model.opcionais.Servico;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicoMapper {
    Servico toModel(ServicoRequestDTO request);
    ServicoResponseDTO toResponse(Servico servico);
    List<ServicoResponseDTO> toResponses(List<Servico> servicos);
}
