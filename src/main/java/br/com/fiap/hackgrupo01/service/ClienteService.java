package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteResponseDTO> buscarClientes();

    ClienteResponseDTO buscarClientePorId(Long id);

    ClienteResponseDTO salvarCliente(ClienteRequestDTO dto);

    ClienteResponseDTO alterarCliente(ClienteRequestDTO dto, Long id);

    void deletarCliente(Long id);
}
