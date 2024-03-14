package br.com.fiap.hackgrupo01.mapper;

import br.com.fiap.hackgrupo01.model.cliente.Cliente;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteRequestDTO;

import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toModel(ClienteRequestDTO dto);
    ClienteResponseDTO toResponse(Cliente cliente);
    List<ClienteResponseDTO> toResponses(List<Cliente> clientes);
}
