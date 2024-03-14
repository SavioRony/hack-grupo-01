package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteDTO;
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

    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        clienteDTO = new ClienteDTO();
        clienteDTO.setId(1L);
        clienteDTO.setNomeCompleto("Fulano");
        clienteDTO.setEmail("fulano@example.com");
        clienteDTO.setCpf("12345678901");
        clienteDTO.setDataNascimento("01/01/1990");
        clienteDTO.setEnderecoPaisOrigem("Brazil");
        clienteDTO.setTelefone("123456789");
        clienteDTO.setPaisOrigem("Brazil");
    }

    @Test
    @DisplayName("Teste para buscar todos os clientes")
    void testFindAll() {
        when(clienteService.findAll()).thenReturn(Collections.singletonList(clienteDTO));

        ResponseEntity<List<ClienteDTO>> responseEntity = clienteController.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.singletonList(clienteDTO), responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para buscar cliente por ID")
    void testFindById() {
        Long id = 1L;
        when(clienteService.findById(id)).thenReturn(clienteDTO);

        ResponseEntity<ClienteDTO> responseEntity = clienteController.findById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clienteDTO, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para cadastrar cliente")
    void testCreate() {
        when(clienteService.create(clienteDTO)).thenReturn(clienteDTO);

        ResponseEntity<ClienteDTO> responseEntity = clienteController.create(clienteDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clienteDTO, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para atualizar cliente")
    void testUpdate() {
        Long id = 1L;
        when(clienteService.update(clienteDTO, id)).thenReturn(clienteDTO);

        ResponseEntity<ClienteDTO> responseEntity = clienteController.update(id, clienteDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clienteDTO, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para deletar cliente")
    void testDelete() {
        Long id = 1L;
        ResponseEntity<?> responseEntity = clienteController.delete(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(clienteService, times(1)).delete(id);
    }
}
