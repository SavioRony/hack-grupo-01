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
        Hospedagem hospedagem = findById(predioRequest.getHospedagem().getId());
        hospedagem.getPredios().add(predioMapper.toModel(predioRequest));
        return hospedagemMapper.toResponse(repository.save(hospedagem));
    }

    @Override
    public HospedagemResponse cadastroQuarto(QuartoRequest request) {
        Hospedagem hospedagem = findByPredioId(request.getPredio().getId());
        for (Predio predio : hospedagem.getPredios()) {
            if (predio.getId().equals(request.getPredio().getId())) {
                predio.getQuartos().add(quartoMapper.toModel(request));
                break;
            }
        }
        return hospedagemMapper.toResponse(repository.save(hospedagem));
    }

    @Override
    public List<HospedagemResponse> buscarHospedagens() {
        return hospedagemMapper.toResponses(repository.findAll());
    }

    @Override
    public HospedagemResponse buscarHospedagemPorId(Long idHospedagem) {
        Hospedagem hospedagem = findById(idHospedagem);
        return hospedagemMapper.toResponse(hospedagem);
    }

    @Override
    public HospedagemResponse buscarHospedagemPorIdPredio(Long idPredio) {
        Hospedagem hospedagem = findByPredioId(idPredio);
        return hospedagemMapper.toResponse(hospedagem);
    }

    @Override
    public HospedagemResponse buscarHospedagemPorIdQuarto(Long idQuarto) {
        Hospedagem hospedagem = findByQuartoId(idQuarto);
        return hospedagemMapper.toResponse(hospedagem);
    }

    @Override
    public HospedagemResponse alteracaoHospedagem(Long idHospedagem, HospedagemRequest request) {
        Hospedagem hospedagem = findById(idHospedagem);
        hospedagem.alteracaoHospedagem(request);
        return hospedagemMapper.toResponse(repository.save(hospedagem));
    }

    @Override
    public HospedagemResponse alteracaoPredio(Long idPredio, PredioUpdateRequest request) {
        Hospedagem hospedagem = findByPredioId(idPredio);
        hospedagem.alteracaoPredio(idPredio, request);
        return hospedagemMapper.toResponse(repository.save(hospedagem));
    }

    @Override
    public HospedagemResponse alteracaoQuarto(Long idQuarto, QuartoUpdateRequest request) {
        Hospedagem hospedagem = findByQuartoId(idQuarto);
        hospedagem.alteracaoQuarto(idQuarto, request);
        return hospedagemMapper.toResponse(repository.save(hospedagem));
    }

    @Override
    public void deleteHospedagem(Long idHospedagem) {
        findById(idHospedagem);
        repository.deleteById(idHospedagem);
    }

    @Override
    public void deletePredio(Long idPredio) {
        findByPredioId(idPredio);
        predioRepository.deleteById(idPredio);
    }

    @Override
    public void deleteQuarto(Long idQuarto) {
        findByQuartoId(idQuarto);
        quartoRepository.deleteById(idQuarto);
    }

    private Hospedagem findByPredioId(Long idPredio) {
        return repository.findByPredioId(idPredio)
                .orElseThrow(() -> {
                    throw new NotFoundException("Hospedagem não encontrada ou id do predio invalido!");
                });
    }

    private Hospedagem findById(Long idHospedagem) {
        return repository.findById(idHospedagem)
                .orElseThrow(() -> {
                    throw new NotFoundException("Hospedagem não encontrada, id invalido!");
                });
    }

    private Hospedagem findByQuartoId(Long idQuarto) {
        return repository.findByQuartoId(idQuarto)
                .orElseThrow(() -> {
                    throw new NotFoundException("Hospedagem não encontrada ou id do quarto invalido!");
                });
    }
}
