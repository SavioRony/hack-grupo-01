package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemResponse;
import br.com.fiap.hackgrupo01.model.hospedagem.Hospedagem;
import br.com.fiap.hackgrupo01.repository.HospedagemRepository;
import br.com.fiap.hackgrupo01.mapper.HospedagemMapper;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequest;
import br.com.fiap.hackgrupo01.service.HospedagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospedagemServiceImpl implements HospedagemService {

    @Autowired
    private HospedagemRepository repository;

    @Autowired
    private HospedagemMapper mapper;
    @Override
    public HospedagemResponse create(HospedagemRequest hospedagemRequest) {
        Hospedagem hospedagem = repository.save(mapper.toModel(hospedagemRequest));
        return mapper.toResponse(hospedagem);
    }
}
