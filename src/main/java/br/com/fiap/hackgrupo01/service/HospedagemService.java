package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.*;

import java.util.List;

public interface HospedagemService {

    HospedagemResponse salvarHospedagem(HospedagemRequest hospedagemRequest);
    HospedagemResponse salvarPredio(PredioRequest predioRequest);
    HospedagemResponse salvarQuarto(QuartoRequest request);
    List<HospedagemResponse> buscarHospedagens();
    HospedagemResponse buscarHospedagemPorId(Long idHospedagem);
    HospedagemResponse buscarHospedagemPorIdPredio(Long idPredio);
    HospedagemResponse buscarHospedagemPorIdQuarto(Long idQuarto);
    HospedagemResponse alterarHospedagem(Long idHospedagem, HospedagemRequest request);
    HospedagemResponse alterarPredio(Long idPredio, PredioUpdateRequest request);
    HospedagemResponse alterarQuarto(Long idQuarto, QuartoUpdateRequest request);
    void deleteHospedagem(Long idHospedagem);
    void deletePredio(Long idPredio);
    void deleteQuarto(Long idQuarto);
}
