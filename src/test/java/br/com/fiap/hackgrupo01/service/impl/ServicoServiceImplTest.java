package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.ServicoMapper;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequestIdDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoUpdateRequestDTO;
import br.com.fiap.hackgrupo01.model.opcionais.Servico;
import br.com.fiap.hackgrupo01.repository.ServicoRepository;
import br.com.fiap.hackgrupo01.service.HospedagemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServicoServiceImplTest {

    @Mock
    private HospedagemService hospedagemService;

    @Mock
    private ServicoRepository servicoRepository;

    @Mock
    private ServicoMapper mapper;

    @InjectMocks
    private ServicoServiceImpl service;

    private ServicoRequestDTO servicoRequest;
    private Servico servico;
    private ServicoResponseDTO servicoResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        servicoRequest = new ServicoRequestDTO();
        HospedagemRequestIdDTO hospedagemRequestId = new HospedagemRequestIdDTO();
        hospedagemRequestId.setId(1L);
        servicoRequest.setHospedagem(hospedagemRequestId);

        servico = new Servico();

        servicoResponse = new ServicoResponseDTO();
    }

    @Test
    @DisplayName("Teste para salvar serviço")
    void testSalvarServico() {
        when(hospedagemService.buscarHospedagemPorId(servicoRequest.getHospedagem().getId())).thenReturn(null);
        when(mapper.toModel(servicoRequest)).thenReturn(servico);
        when(servicoRepository.save(servico)).thenReturn(servico);
        when(mapper.toResponse(servico)).thenReturn(servicoResponse);

        ServicoResponseDTO result = service.salvarServico(servicoRequest);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Teste para alterar serviço")
    void testAlterarServico() {
        long id = 1L;
        ServicoUpdateRequestDTO request = new ServicoUpdateRequestDTO();
        when(servicoRepository.findById(id)).thenReturn(Optional.of(servico));
        when(mapper.toResponse(servico)).thenReturn(servicoResponse);

        ServicoResponseDTO result = service.alterarServico(id, request);

        assertNull(result);
    }

    @Test
    @DisplayName("Teste para listar serviços por hospedagem")
    void testListarServicosPorHospedagem() {
        long idHospedagem = 1L;
        when(hospedagemService.buscarHospedagemPorId(idHospedagem)).thenReturn(null);
        when(servicoRepository.findByHospedagemId(idHospedagem)).thenReturn(Collections.emptyList());
        when(mapper.toResponses(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<ServicoResponseDTO> result = service.listarServicosPorHospedagem(idHospedagem);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Teste para buscar serviço por ID")
    void testBuscarServicoPorId() {
        long id = 1L;
        when(servicoRepository.findById(id)).thenReturn(Optional.of(servico));
        when(mapper.toResponse(servico)).thenReturn(servicoResponse);

        ServicoResponseDTO result = service.buscarServicoPorId(id);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Teste para deletar serviço")
    void testDeletarServico() {
        long id = 1L;
        when(servicoRepository.findById(id)).thenReturn(Optional.of(servico));

        assertDoesNotThrow(() -> service.deletarServico(id));
        verify(servicoRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Teste para buscar serviço por ID - falha")
    void testFindByIdNotFound() {
        long id = 1L;
        when(servicoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.buscarServicoPorId(id));

        verify(mapper, never()).toResponse(any());
    }

}
