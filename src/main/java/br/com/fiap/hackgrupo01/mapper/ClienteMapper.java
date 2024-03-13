package br.com.fiap.hackgrupo01.mapper;

import br.com.fiap.hackgrupo01.model.cliente.Cliente;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteDTO;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toModel(ClienteDTO dto);
    ClienteDTO toResponse(Cliente cliente);
    List<ClienteDTO> toResponses(List<Cliente> clientes);
}
