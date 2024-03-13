package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.ServicoMapper;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoRequest;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoResponse;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoUpdateRequest;
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
    public ServicoResponse salvarServico(ServicoRequest servico) {
        hospedagemService.buscarHospedagemPorId(servico.getHospedagem().getId());
        return mapper.toResponse(servicoRepository.save(mapper.toModel(servico)));
    }

    @Override
    public ServicoResponse alterarServico(Long id, ServicoUpdateRequest request) {
        Servico servico = findById(id);
        servico.altera(request);
        return mapper.toResponse(servicoRepository.save(servico));
    }

    @Override
    public List<ServicoResponse> listarServicosPorHospedagem(Long idHospedagem) {
        hospedagemService.buscarHospedagemPorId(idHospedagem);
        return mapper.toResponses(servicoRepository.findByHospedagemId(idHospedagem));
    }

    @Override
    public ServicoResponse buscarServicoPorId(Long id) {
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
