package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.*;
import br.com.fiap.hackgrupo01.service.HospedagemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HospedagemControllerTest {

    @Mock
    private HospedagemService service;

    @InjectMocks
    private HospedagemController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste para cadastrar uma hospedagem")
    void testCadastroHospedagem() {
        HospedagemRequestDTO request = new HospedagemRequestDTO();
        HospedagemResponseDTO response = new HospedagemResponseDTO();
        when(service.salvarHospedagem(request)).thenReturn(response);

        ResponseEntity<HospedagemResponseDTO> result = controller.cadastroHospedagem(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    @DisplayName("Teste para cadastrar um prédio")
    void testCadastroPredio() {
        PredioRequestDTO request = new PredioRequestDTO();
        HospedagemResponseDTO response = new HospedagemResponseDTO();
        when(service.salvarPredio(request)).thenReturn(response);

        ResponseEntity<HospedagemResponseDTO> result = controller.cadastroPredio(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    @DisplayName("Teste para cadastrar um quarto")
    void testCadastroQuarto() {
        QuartoRequestDTO request = new QuartoRequestDTO();
        HospedagemResponseDTO response = new HospedagemResponseDTO();
        when(service.salvarQuarto(request)).thenReturn(response);

        ResponseEntity<HospedagemResponseDTO> result = controller.cadastroQuarto(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    @DisplayName("Teste para buscar todas as hospedagens")
    void testGetHospedagens() {
        List<HospedagemResponseDTO> hospedagens = new ArrayList<>();
        when(service.buscarHospedagens()).thenReturn(hospedagens);

        ResponseEntity<List<HospedagemResponseDTO>> result = controller.getHospedagens();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(hospedagens, result.getBody());
    }

    @Test
    @DisplayName("Teste para buscar uma hospedagem pelo ID")
    void testGetHospedagemById() {
        long idHospedagem = 1L;
        HospedagemResponseDTO hospedagem = new HospedagemResponseDTO();
        when(service.buscarHospedagemPorId(idHospedagem)).thenReturn(hospedagem);

        ResponseEntity<HospedagemResponseDTO> result = controller.getHospedagemById(idHospedagem);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(hospedagem, result.getBody());
    }

    @Test
    @DisplayName("Teste para buscar uma hospedagem pelo ID do prédio")
    void testGetHospedagemByIdPredio() {
        long idPredio = 1L;
        HospedagemResponseDTO hospedagem = new HospedagemResponseDTO();
        when(service.buscarHospedagemPorIdPredio(idPredio)).thenReturn(hospedagem);

        ResponseEntity<HospedagemResponseDTO> result = controller.getHospedagemByIdPredio(idPredio);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(hospedagem, result.getBody());
    }

    @Test
    @DisplayName("Teste para buscar uma hospedagem pelo ID do quarto")
    void testGetHospedagemByIdQuarto() {
        long idQuarto = 1L;
        HospedagemResponseDTO hospedagem = new HospedagemResponseDTO();
        when(service.buscarHospedagemPorIdQuarto(idQuarto)).thenReturn(hospedagem);

        ResponseEntity<?> result = controller.getHospedagemByIdQuarto(idQuarto);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(hospedagem, result.getBody());
    }

    @Test
    @DisplayName("Teste para alterar uma hospedagem")
    void testAlteracaoHospedagem() {
        long idHospedagem = 1L;
        HospedagemRequestDTO request = new HospedagemRequestDTO();
        HospedagemResponseDTO response = new HospedagemResponseDTO();
        when(service.alterarHospedagem(idHospedagem, request)).thenReturn(response);

        ResponseEntity<HospedagemResponseDTO> result = controller.alteracaoHospedagem(idHospedagem, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    @DisplayName("Teste para alterar um prédio")
    void testAlteracaoPredio() {
        long idPredio = 1L;
        PredioUpdateRequestDTO request = new PredioUpdateRequestDTO();
        HospedagemResponseDTO response = new HospedagemResponseDTO();
        when(service.alterarPredio(idPredio, request)).thenReturn(response);

        ResponseEntity<HospedagemResponseDTO> result = controller.alteracaoPredio(idPredio, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    @DisplayName("Teste para alterar um quarto")
    void testAlteracaoQuarto() {
        long idQuarto = 1L;
        QuartoUpdateRequestDTO request = new QuartoUpdateRequestDTO();
        HospedagemResponseDTO response = new HospedagemResponseDTO();
        when(service.alterarQuarto(idQuarto, request)).thenReturn(response);

        ResponseEntity<?> result = controller.alteracaoQuarto(idQuarto, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    @DisplayName("Teste para excluir uma hospedagem por ID")
    void testExclusaoHospedagem() {
        long idHospedagem = 1L;
        doNothing().when(service).deleteHospedagem(idHospedagem);

        ResponseEntity<Void> result = controller.alteracaoStatusHospedagem(idHospedagem);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(service).deleteHospedagem(idHospedagem);
    }

    @Test
    @DisplayName("Teste para excluir um prédio por ID")
    void testExclusaoPredio() {
        long idPredio = 1L;
        doNothing().when(service).deletePredio(idPredio);

        ResponseEntity<Void> result = controller.alteracaoStatusPredio(idPredio);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(service).deletePredio(idPredio);
    }

    @Test
    @DisplayName("Teste para excluir um quarto por ID")
    void testExclusaoQuarto() {
        long idQuarto = 1L;
        doNothing().when(service).deleteQuarto(idQuarto);

        ResponseEntity<Void> result = controller.alteracaoStatusQuarto(idQuarto);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(service).deleteQuarto(idQuarto);
    }
}