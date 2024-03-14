package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemUpdateRequestDTO;
import br.com.fiap.hackgrupo01.service.ItemService;
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

class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    private ItemRequestDTO itemRequest;
    private ItemResponseDTO itemResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        itemRequest = new ItemRequestDTO();
        itemRequest.setNome("Refrigerante");
        itemRequest.setValor(5.0);

        itemResponse = new ItemResponseDTO();
        itemResponse.setId(1L);
        itemResponse.setNome("Refrigerante");
        itemResponse.setValor(5.0);
    }

    @Test
    @DisplayName("Teste para cadastrar item")
    void testSalvarItem() {
        when(itemService.salvarItem(itemRequest)).thenReturn(itemResponse);

        ResponseEntity<ItemResponseDTO> responseEntity = itemController.salvarItem(itemRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(itemResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para alterar item")
    void testAlterarItem() {
        long id = 1L;
        ItemUpdateRequestDTO itemUpdateRequest = new ItemUpdateRequestDTO();
        itemUpdateRequest.setNome("Refrigerante");
        itemUpdateRequest.setValor(6.0);
        when(itemService.alterarItem(id, itemUpdateRequest)).thenReturn(itemResponse);

        ResponseEntity<ItemResponseDTO> responseEntity = itemController.alterarItem(id, itemUpdateRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(itemResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para buscar item por ID")
    void testBuscarItemPorId() {
        long id = 1L;
        when(itemService.buscarItemPorId(id)).thenReturn(itemResponse);

        ResponseEntity<ItemResponseDTO> responseEntity = itemController.buscarItemPorId(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(itemResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para deletar item por ID")
    void testDeletarItem() {
        long id = 1L;
        ResponseEntity<Void> responseEntity = itemController.deletarItem(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(itemService, times(1)).deletarItem(id);
    }

    @Test
    @DisplayName("Teste para listar itens por hospedagem")
    void testListarItens() {
        long idHospedagem = 1L;
        List<ItemResponseDTO> itemResponses = Collections.singletonList(itemResponse);
        when(itemService.listarItensPorHospedagem(idHospedagem)).thenReturn(itemResponses);

        ResponseEntity<List<ItemResponseDTO>> responseEntity = itemController.listarItens(idHospedagem);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(itemResponses, responseEntity.getBody());
    }
}
