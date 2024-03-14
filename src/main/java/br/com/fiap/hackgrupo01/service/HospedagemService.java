package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.*;

import java.util.List;

public interface HospedagemService {

    HospedagemResponseDTO salvarHospedagem(HospedagemRequestDTO hospedagemRequest);
    HospedagemResponseDTO salvarPredio(PredioRequestDTO predioRequest);
    HospedagemResponseDTO salvarQuarto(QuartoRequestDTO request);
    List<HospedagemResponseDTO> buscarHospedagens();
    HospedagemResponseDTO buscarHospedagemPorId(Long idHospedagem);
    HospedagemResponseDTO buscarHospedagemPorIdPredio(Long idPredio);
    HospedagemResponseDTO buscarHospedagemPorIdQuarto(Long idQuarto);
    HospedagemResponseDTO alterarHospedagem(Long idHospedagem, HospedagemRequestDTO request);
    HospedagemResponseDTO alterarPredio(Long idPredio, PredioUpdateRequestDTO request);
    HospedagemResponseDTO alterarQuarto(Long idQuarto, QuartoUpdateRequestDTO request);
    void deleteHospedagem(Long idHospedagem);
    void deletePredio(Long idPredio);
    void deleteQuarto(Long idQuarto);
}
