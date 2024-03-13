package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoRequest;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoResponse;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoUpdateRequest;

import java.util.List;

public interface ServicoService {
    ServicoResponse salvarServico(ServicoRequest servico);
    ServicoResponse alterarServico(Long id, ServicoUpdateRequest servico);

    List<ServicoResponse> listarServicosPorHospedagem(Long idHospedagem);

    ServicoResponse buscarServicoPorId(Long id);

    void deletarServico(Long id);
}
