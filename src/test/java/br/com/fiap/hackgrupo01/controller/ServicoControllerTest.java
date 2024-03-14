package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoRequest;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoResponse;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoUpdateRequest;
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

    private ServicoRequest servicoRequest;
    private ServicoResponse servicoResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        servicoRequest = new ServicoRequest();
        servicoRequest.setNome("Wi-Fi");
        servicoRequest.setValor(10.0);

        servicoResponse = new ServicoResponse();
        servicoResponse.setId(1L);
        servicoResponse.setNome("Wi-Fi");
        servicoResponse.setValor(10.0);
    }

    @Test
    @DisplayName("Teste para cadastrar serviço")
    void testSalvarServico() {
        when(servicoService.salvarServico(servicoRequest)).thenReturn(servicoResponse);

        ResponseEntity<ServicoResponse> responseEntity = servicoController.salvarServico(servicoRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(servicoResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para alterar serviço")
    void testAlterarServico() {
        long id = 1L;
        ServicoUpdateRequest servicoUpdateRequest = new ServicoUpdateRequest();
        servicoUpdateRequest.setNome("Wi-Fi");
        servicoUpdateRequest.setValor(15.0);
        when(servicoService.alterarServico(id, servicoUpdateRequest)).thenReturn(servicoResponse);

        ResponseEntity<ServicoResponse> responseEntity = servicoController.alterarServico(id, servicoUpdateRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(servicoResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para buscar serviço por hospedagem")
    void testListarServicosPorHospedagem() {
        long idHospedagem = 1L;
        List<ServicoResponse> servicoResponses = Collections.singletonList(servicoResponse);
        when(servicoService.listarServicosPorHospedagem(idHospedagem)).thenReturn(servicoResponses);

        ResponseEntity<List<ServicoResponse>> responseEntity = servicoController.listarServicosPorHospedagem(idHospedagem);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(servicoResponses, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para buscar serviço por ID")
    void testBuscarServicoPorId() {
        long id = 1L;
        when(servicoService.buscarServicoPorId(id)).thenReturn(servicoResponse);

        ResponseEntity<ServicoResponse> responseEntity = servicoController.buscarServicoPorId(id);

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
