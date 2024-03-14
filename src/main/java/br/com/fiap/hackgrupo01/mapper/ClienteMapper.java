package br.com.fiap.hackgrupo01.mapper;

import br.com.fiap.hackgrupo01.model.cliente.Cliente;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteRequest;

import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toModel(ClienteRequest dto);
    ClienteResponse toResponse(Cliente cliente);
    List<ClienteResponse> toResponses(List<Cliente> clientes);
}
