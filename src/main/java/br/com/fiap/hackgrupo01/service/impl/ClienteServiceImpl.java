package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.BadRequestException;
import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.ClienteMapper;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteDTO;
import br.com.fiap.hackgrupo01.repository.ClienteRepository;
import br.com.fiap.hackgrupo01.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteMapper mapper;

    private static final String NOT_FOUND_MESSAGE= "Não foi encontrado cliente com id ";

    @Override
    public List<ClienteDTO> findAll() {
        return mapper.toResponses(repository.findAll());
    }

    @Override
    public ClienteDTO findById(Long id) {
        var response = repository.findById(id).map(cliente -> mapper.toResponse(cliente)).orElse(null);
        if(response != null){
            return response;
        }
        throw new NotFoundException(NOT_FOUND_MESSAGE.concat(id.toString()));
    }

    @Override
    public ClienteDTO create(ClienteDTO dto) {
        return mapper.toResponse(repository.save(mapper.toModel(dto)));
    }

    @Override
    public ClienteDTO update(ClienteDTO dto, Long id) {
        var response = repository.findById(id);
        if(response.isPresent()){
            return mapper.toResponse(repository.save(mapper.toModel(dto)));
        }
        throw new NotFoundException(NOT_FOUND_MESSAGE.concat(id.toString()));
    }

    @Override
    public void delete(Long id) {
        var response = repository.findById(id);
        if(response.isPresent()){
            repository.deleteById(id);
        }else {
            throw new NotFoundException(NOT_FOUND_MESSAGE.concat(id.toString()));
        }
    }
}
