package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.HospedagemMapper;
import br.com.fiap.hackgrupo01.mapper.PredioMapper;
import br.com.fiap.hackgrupo01.mapper.QuartoMapper;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.*;
import br.com.fiap.hackgrupo01.model.hospedagem.Hospedagem;
import br.com.fiap.hackgrupo01.model.hospedagem.Predio;
import br.com.fiap.hackgrupo01.repository.HospedagemRepository;
import br.com.fiap.hackgrupo01.repository.PredioRepository;
import br.com.fiap.hackgrupo01.repository.QuartoRepository;
import br.com.fiap.hackgrupo01.service.HospedagemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospedagemServiceImpl implements HospedagemService {

    private final HospedagemRepository repository;

    private final QuartoRepository quartoRepository;

    private final PredioRepository predioRepository;

    private final HospedagemMapper hospedagemMapper;

    private final PredioMapper predioMapper;

    private final QuartoMapper quartoMapper;

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
            throw new NotFoundException("Hospedagem não encontrada, id invalido!");
        }
    }

    @Override
    public HospedagemResponse cadastroQuarto(QuartoRequest request) {
        Hospedagem hospedagem = repository.findByPredioId(request.getPredio().getId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Predio não encontrado, id invalido!");
                });
        for (Predio predio : hospedagem.getPredios()) {
            if (predio.getId().equals(request.getPredio().getId())) {
                predio.getQuartos().add(quartoMapper.toModel(request));
                break;
            }
        }
        return hospedagemMapper.toResponse(repository.save(hospedagem));
    }

    @Override
    public List<HospedagemResponse> getHospedagens() {
        return hospedagemMapper.toResponses(repository.findAll());
    }

    @Override
    public HospedagemResponse getHospedagemById(Long idHospedagem) {
        Hospedagem hospedagem = repository.findById(idHospedagem)
                .orElseThrow(() -> {
                    throw new NotFoundException("Hospedagem não encontrada, id invalido!");
                });
        return hospedagemMapper.toResponse(hospedagem);
    }

    @Override
    public HospedagemResponse getHospedagemByIdPredio(Long idPredio) {
        Hospedagem hospedagem = repository.findByPredioId(idPredio)
                .orElseThrow(() -> {
                    throw new NotFoundException("Hospedagem não encontrada ou id do predio invalido!");
                });
        return hospedagemMapper.toResponse(hospedagem);
    }

    @Override
    public HospedagemResponse getHospedagemByIdQuarto(Long idQuarto) {
        Hospedagem hospedagem = repository.findByQuartoId(idQuarto)
                .orElseThrow(() -> {
                    throw new NotFoundException("Hospedagem não encontrada ou id do quarto invalido!");
                });
        return hospedagemMapper.toResponse(hospedagem);
    }

    @Override
    public HospedagemResponse alteracaoHospedagem(Long idHospedagem, HospedagemRequest request) {
        Hospedagem hospedagem = repository.findById(idHospedagem)
                .orElseThrow(() -> {
                    throw new NotFoundException("Hospedagem não encontrada, id invalido!");
                });

        hospedagem.alteracaoHospedagem(request);
        return hospedagemMapper.toResponse(repository.save(hospedagem));
    }

    @Override
    public HospedagemResponse alteracaoPredio(Long idPredio, PredioUpdateRequest request) {
        Hospedagem hospedagem = repository.findByPredioId(idPredio)
                .orElseThrow(() -> {
                    throw new NotFoundException("Hospedagem não encontrada ou id do predio invalido!");
                });
        hospedagem.alteracaoPredio(idPredio, request);
        return hospedagemMapper.toResponse(repository.save(hospedagem));
    }

    @Override
    public HospedagemResponse alteracaoQuarto(Long idQuarto, QuartoUpdateRequest request) {
        Hospedagem hospedagem = repository.findByQuartoId(idQuarto)
                .orElseThrow(() -> {
                    throw new NotFoundException("Hospedagem não encontrada ou id do quarto invalido!");
                });
        hospedagem.alteracaoQuarto(idQuarto, request);
        return hospedagemMapper.toResponse(repository.save(hospedagem));
    }

    @Override
    public void deleteHospedagem(Long idHospedagem) {
        repository.findById(idHospedagem)
                .orElseThrow(() -> {
                    throw new NotFoundException("Hospedagem não encontrada, id invalido!");
                });
        repository.deleteById(idHospedagem);
    }

    @Override
    public void deletePredio(Long idPredio) {
        repository.findByPredioId(idPredio)
                .orElseThrow(() -> {
                    throw new NotFoundException("Hospedagem não encontrada ou id do predio invalido!");
                });
        predioRepository.deleteById(idPredio);
    }

    @Override
    public void deleteQuarto(Long idQuarto) {
        repository.findByQuartoId(idQuarto)
                .orElseThrow(() -> {
                    throw new NotFoundException("Hospedagem não encontrada ou id do quarto invalido!");
                });

        quartoRepository.deleteById(idQuarto);
    }
}
