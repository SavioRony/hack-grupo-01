package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.ClienteMapper;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteResponseDTO;
import br.com.fiap.hackgrupo01.repository.ClienteRepository;
import br.com.fiap.hackgrupo01.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    private final ClienteMapper mapper;

    private static final String NOT_FOUND_MESSAGE= "Não foi encontrado cliente com id ";

    @Override
    public List<ClienteResponseDTO> buscarClientes() {
        return mapper.toResponses(repository.findAll());
    }

    @Override
    public ClienteResponseDTO buscarClientePorId(Long id) {
        return repository.findById(id).map(cliente -> mapper.toResponse(cliente)).orElseThrow(() -> {
            throw new NotFoundException(NOT_FOUND_MESSAGE.concat(id.toString()));
        });
    }

    @Override
    public ClienteResponseDTO salvarCliente(ClienteRequestDTO dto) {
        return mapper.toResponse(repository.save(mapper.toModel(dto)));
    }

    @Override
    public ClienteResponseDTO alterarCliente(ClienteRequestDTO dto, Long id) {
        var response = repository.findById(id);
        if(response.isPresent()){
            return mapper.toResponse(repository.save(mapper.toModel(dto)));
        }
        throw new NotFoundException(NOT_FOUND_MESSAGE.concat(id.toString()));
    }

    @Override
    public void deletarCliente(Long id) {
        var response = repository.findById(id);
        if(response.isPresent()){
            repository.deleteById(id);
        }else {
            throw new NotFoundException(NOT_FOUND_MESSAGE.concat(id.toString()));
        }
    }
}
