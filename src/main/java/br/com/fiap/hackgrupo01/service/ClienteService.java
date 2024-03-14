package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteRequest;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteResponse;

import java.util.List;

public interface ClienteService {

    List<ClienteResponse> buscarClientes();

    ClienteResponse buscarClientePorId(Long id);

    ClienteResponse salvarCliente(ClienteRequest dto);

    ClienteResponse alterarCliente(ClienteRequest dto, Long id);

    void deletarCliente(Long id);
}
