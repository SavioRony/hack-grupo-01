package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoUpdateRequestDTO;
import br.com.fiap.hackgrupo01.service.ServicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServicoControllerTest {

    @Mock
    private ServicoService servicoService;

    @InjectMocks
    private ServicoController servicoController;

    private ServicoRequestDTO servicoRequest;
    private ServicoResponseDTO servicoResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        servicoRequest = new ServicoRequestDTO();
        servicoRequest.setNome("Wi-Fi");
        servicoRequest.setValor(10.0);

        servicoResponse = new ServicoResponseDTO();
        servicoResponse.setId(1L);
        servicoResponse.setNome("Wi-Fi");
        servicoResponse.setValor(10.0);
    }

    @Test
    @DisplayName("Teste para cadastrar serviço")
    void testSalvarServico() {
        when(servicoService.salvarServico(servicoRequest)).thenReturn(servicoResponse);

        ResponseEntity<ServicoResponseDTO> responseEntity = servicoController.salvarServico(servicoRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(servicoResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para alterar serviço")
    void testAlterarServico() {
        long id = 1L;
        ServicoUpdateRequestDTO servicoUpdateRequest = new ServicoUpdateRequestDTO();
        servicoUpdateRequest.setNome("Wi-Fi");
        servicoUpdateRequest.setValor(15.0);
        when(servicoService.alterarServico(id, servicoUpdateRequest)).thenReturn(servicoResponse);

        ResponseEntity<ServicoResponseDTO> responseEntity = servicoController.alterarServico(id, servicoUpdateRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(servicoResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para buscar serviço por hospedagem")
    void testListarServicosPorHospedagem() {
        long idHospedagem = 1L;
        List<ServicoResponseDTO> servicoResponses = Collections.singletonList(servicoResponse);
        when(servicoService.listarServicosPorHospedagem(idHospedagem)).thenReturn(servicoResponses);

        ResponseEntity<List<ServicoResponseDTO>> responseEntity = servicoController.listarServicosPorHospedagem(idHospedagem);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(servicoResponses, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para buscar serviço por ID")
    void testBuscarServicoPorId() {
        long id = 1L;
        when(servicoService.buscarServicoPorId(id)).thenReturn(servicoResponse);

        ResponseEntity<ServicoResponseDTO> responseEntity = servicoController.buscarServicoPorId(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(servicoResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para deletar serviço por ID")
    void testDeletarServico() {
        long id = 1L;
        ResponseEntity<Void> responseEntity = servicoController.deletarServico(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(servicoService, times(1)).deletarServico(id);
    }
}
