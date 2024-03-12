package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.BadRequestException;
import br.com.fiap.hackgrupo01.mapper.PredioMapper;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemResponse;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.PredioRequest;
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
    private HospedagemMapper hospedagemMapper;

    @Autowired
    private PredioMapper predioMapper;
    @Override
    public HospedagemResponse cadastroHospedagem(HospedagemRequest hospedagemRequest) {
        Hospedagem hospedagem = repository.save(hospedagemMapper.toModel(hospedagemRequest));
        return hospedagemMapper.toResponse(hospedagem);
    }

    @Override
    public HospedagemResponse cadastroPredio(PredioRequest predioRequest) {
        Hospedagem hospedagem = repository.getReferenceById(predioRequest.getHospedagem().getId());
        if (hospedagem.getId() == null){
            throw new BadRequestException("Hospedagem não encontrada id invalido!");
        }
        hospedagem.getPredios().add(predioMapper.toModel(predioRequest));
        return hospedagemMapper.toResponse(repository.save(hospedagem));
    }
}
