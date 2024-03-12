package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequest;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemResponse;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.PredioRequest;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.QuartoRequest;

public interface HospedagemService {

    HospedagemResponse cadastroHospedagem(HospedagemRequest hospedagemRequest);
    HospedagemResponse cadastroPredio(PredioRequest predioRequest);
    HospedagemResponse cadastroQuarto(QuartoRequest request);
}
