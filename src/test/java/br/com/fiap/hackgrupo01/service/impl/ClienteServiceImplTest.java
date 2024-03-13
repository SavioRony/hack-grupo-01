package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.ClienteMapper;
import br.com.fiap.hackgrupo01.model.cliente.Cliente;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteDTO;
import br.com.fiap.hackgrupo01.repository.ClienteRepository;
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

class ClienteServiceImplTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private ClienteMapper mapper;

    @InjectMocks
    private ClienteServiceImpl service;

    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        clienteDTO = new ClienteDTO();
        clienteDTO.setId(1L);
        clienteDTO.setNomeCompleto("João Silva");
        clienteDTO.setCpf("12345678900");
        clienteDTO.setEmail("joao@example.com");
        clienteDTO.setTelefone("(11) 98765-4321");
    }

    @Test
    @DisplayName("Teste para buscar todos os clientes")
    void testFindAll() {
        List<ClienteDTO> clientes = Collections.singletonList(clienteDTO);
        when(repository.findAll()).thenReturn(Collections.emptyList());
        when(mapper.toResponses(Collections.emptyList())).thenReturn(clientes);

        List<ClienteDTO> result = service.findAll();

        assertEquals(clientes, result);
    }

    @Test
    @DisplayName("Teste para buscar cliente por ID - sucesso")
    void testFindByIdSuccess() {
        long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(new Cliente()));
        when(mapper.toResponse(any())).thenReturn(clienteDTO);

        ClienteDTO result = service.findById(id);

        assertNotNull(result);
        assertEquals(clienteDTO, result);
    }

    @Test
    @DisplayName("Teste para buscar cliente por ID - falha")
    void testFindByIdNotFound() {
        long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.findById(id));
    }

    @Test
    @DisplayName("Teste para criar cliente")
    void testCreate() {
        when(repository.save(any())).thenReturn(new Cliente());
        when(mapper.toResponse(any())).thenReturn(clienteDTO);

        ClienteDTO result = service.create(clienteDTO);

        assertNotNull(result);
        assertEquals(clienteDTO, result);
    }

    @Test
    @DisplayName("Teste para atualizar cliente - sucesso")
    void testUpdateSuccess() {
        long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(new Cliente()));
        when(repository.save(any())).thenReturn(new Cliente());
        when(mapper.toResponse(any())).thenReturn(clienteDTO);

        ClienteDTO result = service.update(clienteDTO, id);

        assertNotNull(result);
        assertEquals(clienteDTO, result);
    }

    @Test
    @DisplayName("Teste para atualizar cliente - falha")
    void testUpdateNotFound() {
        long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.update(clienteDTO, id));
    }

    @Test
    @DisplayName("Teste para deletar cliente - sucesso")
    void testDeleteSuccess() {
        long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(new Cliente()));

        assertDoesNotThrow(() -> service.delete(id));
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Teste para deletar cliente - falha")
    void testDeleteNotFound() {
        long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.delete(id));
    }
}
