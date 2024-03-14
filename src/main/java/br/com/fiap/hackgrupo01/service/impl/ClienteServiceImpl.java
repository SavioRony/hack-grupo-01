package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.ClienteMapper;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteRequest;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteResponse;
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
    public List<ClienteResponse> buscarClientes() {
        return mapper.toResponses(repository.findAll());
    }

    @Override
    public ClienteResponse buscarClientePorId(Long id) {
        var response = repository.findById(id).map(mapper::toResponse).orElse(null);
        if(response != null){
            return response;
        }
        throw new NotFoundException(NOT_FOUND_MESSAGE.concat(id.toString()));
    }

    @Override
    public ClienteResponse salvarCliente(ClienteRequest dto) {
        return mapper.toResponse(repository.save(mapper.toModel(dto)));
    }

    @Override
    public ClienteResponse alterarCliente(ClienteRequest dto, Long id) {
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
