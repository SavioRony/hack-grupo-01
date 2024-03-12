package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.BadRequestException;
import br.com.fiap.hackgrupo01.mapper.HospedagemMapper;
import br.com.fiap.hackgrupo01.mapper.PredioMapper;
import br.com.fiap.hackgrupo01.mapper.QuartoMapper;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequest;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemResponse;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.PredioRequest;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.QuartoRequest;
import br.com.fiap.hackgrupo01.model.hospedagem.Hospedagem;
import br.com.fiap.hackgrupo01.model.hospedagem.Predio;
import br.com.fiap.hackgrupo01.repository.HospedagemRepository;
import br.com.fiap.hackgrupo01.service.HospedagemService;
import jakarta.persistence.EntityNotFoundException;
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

    @Autowired
    private QuartoMapper quartoMapper;

    @Override
    public HospedagemResponse cadastroHospedagem(HospedagemRequest hospedagemRequest) {
        Hospedagem hospedagem = repository.save(hospedagemMapper.toModel(hospedagemRequest));
        return hospedagemMapper.toResponse(hospedagem);
    }

    @Override
    public HospedagemResponse cadastroPredio(PredioRequest predioRequest) {
        try {
            Hospedagem hospedagem = repository.getReferenceById(predioRequest.getHospedagem().getId());
            hospedagem.getPredios().add(predioMapper.toModel(predioRequest));
            return hospedagemMapper.toResponse(repository.save(hospedagem));
        } catch (EntityNotFoundException e) {
            throw new BadRequestException("Hospedagem não encontrada id invalido!");
        }
    }

    @Override
    public HospedagemResponse cadastroQuarto(QuartoRequest request) {
        Hospedagem hospedagem = repository.findByPredioId(request.getPredio().getId())
                .orElseThrow(() -> {throw new BadRequestException("Predio não encontrado id invalido!");});
        for (Predio predio : hospedagem.getPredios()) {
            if (predio.getId().equals(request.getPredio().getId())) {
                predio.getQuartos().add(quartoMapper.toModel(request));
                break;
            }
        }
        return hospedagemMapper.toResponse(repository.save(hospedagem));
    }
}
