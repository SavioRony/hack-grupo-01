package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> findAll();

    ClienteDTO findById(Long id);

    ClienteDTO create(ClienteDTO dto);

    ClienteDTO update(ClienteDTO dto, Long id);

    void delete(Long id);
}
