package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.ServicoMapper;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoUpdateRequestDTO;
import br.com.fiap.hackgrupo01.model.opcionais.Servico;
import br.com.fiap.hackgrupo01.repository.ServicoRepository;
import br.com.fiap.hackgrupo01.service.HospedagemService;
import br.com.fiap.hackgrupo01.service.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoServiceImpl implements ServicoService {

    private final HospedagemService hospedagemService;

    private final ServicoRepository servicoRepository;

    private final ServicoMapper mapper;

    @Override
    public ServicoResponseDTO salvarServico(ServicoRequestDTO servico) {
        hospedagemService.buscarHospedagemPorId(servico.getHospedagem().getId());
        return mapper.toResponse(servicoRepository.save(mapper.toModel(servico)));
    }

    @Override
    public ServicoResponseDTO alterarServico(Long id, ServicoUpdateRequestDTO request) {
        Servico servico = findById(id);
        servico.altera(request);
        return mapper.toResponse(servicoRepository.save(servico));
    }

    @Override
    public List<ServicoResponseDTO> listarServicosPorHospedagem(Long idHospedagem) {
        hospedagemService.buscarHospedagemPorId(idHospedagem);
        return mapper.toResponses(servicoRepository.findByHospedagemId(idHospedagem));
    }

    @Override
    public ServicoResponseDTO buscarServicoPorId(Long id) {
        return mapper.toResponse(findById(id));
    }

    @Override
    public void deletarServico(Long id) {
        buscarServicoPorId(id);
        servicoRepository.deleteById(id);
    }

    private Servico findById(Long id){
        return servicoRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Serviço não encontrado");
        });
    }
}
