package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoUpdateRequestDTO;

import java.util.List;

public interface ServicoService {
    ServicoResponseDTO salvarServico(ServicoRequestDTO servico);
    ServicoResponseDTO alterarServico(Long id, ServicoUpdateRequestDTO servico);

    List<ServicoResponseDTO> listarServicosPorHospedagem(Long idHospedagem);

    ServicoResponseDTO buscarServicoPorId(Long id);

    void deletarServico(Long id);
}
