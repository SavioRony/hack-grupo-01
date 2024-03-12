package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequest;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemResponse;

public interface HospedagemService {

    HospedagemResponse create(HospedagemRequest hospedagemRequest);
}
