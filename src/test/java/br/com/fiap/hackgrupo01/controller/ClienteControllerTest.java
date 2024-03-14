package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteRequest;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteResponse;
import br.com.fiap.hackgrupo01.service.ClienteService;
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

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private ClienteResponse clienteResponse;
    private ClienteRequest clienteRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        clienteResponse = new ClienteResponse();
        clienteResponse.setNomeCompleto("Fulano");
        clienteResponse.setEmail("fulano@example.com");
        clienteResponse.setCpf("12345678901");
        clienteResponse.setDataNascimento("01/01/1990");
        clienteResponse.setEnderecoPaisOrigem("Brazil");
        clienteResponse.setTelefone("123456789");
        clienteResponse.setPaisOrigem("Brasil");

        clienteRequest = new ClienteRequest();
        clienteRequest.setNomeCompleto("Fulano");
        clienteRequest.setEmail("fulano@example.com");
        clienteRequest.setCpf("12345678901");
        clienteRequest.setDataNascimento("01/01/1990");
        clienteRequest.setEnderecoPaisOrigem("Brazil");
        clienteRequest.setTelefone("123456789");
        clienteRequest.setPaisOrigem("Brasil");
    }

    @Test
    @DisplayName("Teste para buscar todos os clientes")
    void testFindAll() {
        when(clienteService.buscarClientes()).thenReturn(Collections.singletonList(clienteResponse));

        ResponseEntity<List<ClienteResponse>> responseEntity = clienteController.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.singletonList(clienteResponse), responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para buscar cliente por ID")
    void testFindById() {
        Long id = 1L;
        when(clienteService.buscarClientePorId(id)).thenReturn(clienteResponse);

        ResponseEntity<ClienteResponse> responseEntity = clienteController.findById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clienteResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para cadastrar cliente")
    void testCreate() {
        when(clienteService.salvarCliente(clienteRequest)).thenReturn(clienteResponse);

        ResponseEntity<ClienteResponse> responseEntity = clienteController.create(clienteRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clienteResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para atualizar cliente")
    void testUpdate() {
        Long id = 1L;
        when(clienteService.alterarCliente(clienteRequest, id)).thenReturn(clienteResponse);

        ResponseEntity<ClienteResponse> responseEntity = clienteController.update(id, clienteRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clienteResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para deletar cliente")
    void testDelete() {
        Long id = 1L;
        ResponseEntity<?> responseEntity = clienteController.delete(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(clienteService, times(1)).deletarCliente(id);
    }
}
