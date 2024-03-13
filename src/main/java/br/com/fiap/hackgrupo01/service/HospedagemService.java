package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.*;

import java.util.List;

public interface HospedagemService {

    HospedagemResponse cadastroHospedagem(HospedagemRequest hospedagemRequest);
    HospedagemResponse cadastroPredio(PredioRequest predioRequest);
    HospedagemResponse cadastroQuarto(QuartoRequest request);
    List<HospedagemResponse> buscarHospedagens();
    HospedagemResponse buscarHospedagemPorId(Long idHospedagem);
    HospedagemResponse buscarHospedagemPorIdPredio(Long idPredio);
    HospedagemResponse buscarHospedagemPorIdQuarto(Long idQuarto);
    HospedagemResponse alteracaoHospedagem(Long idHospedagem, HospedagemRequest request);
    HospedagemResponse alteracaoPredio(Long idPredio, PredioUpdateRequest request);
    HospedagemResponse alteracaoQuarto(Long idQuarto, QuartoUpdateRequest request);
    void deleteHospedagem(Long idHospedagem);
    void deletePredio(Long idPredio);
    void deleteQuarto(Long idQuarto);
}
