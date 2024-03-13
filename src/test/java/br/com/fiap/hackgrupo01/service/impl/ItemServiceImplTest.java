package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.ItemMapper;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequestId;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemResponse;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemRequest;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemResponse;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemUpdateRequest;
import br.com.fiap.hackgrupo01.model.hospedagem.Hospedagem;
import br.com.fiap.hackgrupo01.model.opcionais.Item;
import br.com.fiap.hackgrupo01.repository.ItemRepository;
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

class ItemServiceImplTest {

    @Mock
    private HospedagemService hospedagemService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemMapper mapper;

    @InjectMocks
    private ItemServiceImpl service;

    private ItemRequest itemRequest;
    private ItemUpdateRequest itemUpdateRequest;
    private Item item;
    private ItemResponse itemResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        itemRequest = new ItemRequest();
        HospedagemRequestId hospedagemRequestId = new HospedagemRequestId();
        hospedagemRequestId.setId(1L);
        itemRequest.setHospedagem(hospedagemRequestId);
        itemUpdateRequest = new ItemUpdateRequest();
        item = new Item();
        itemResponse = new ItemResponse();
    }

    @Test
    @DisplayName("Teste para salvar item - sucesso")
    void testSalvarItemSuccess() {
        when(hospedagemService.buscarHospedagemPorId(anyLong())).thenReturn(new HospedagemResponse());
        when(itemRepository.save(any())).thenReturn(item);
        when(mapper.toResponse(any())).thenReturn(itemResponse);

        ItemResponse result = service.salvarItem(itemRequest);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Teste para alterar item - sucesso")
    void testAlterarItemSuccess() {
        long id = 1L;
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));
        when(itemRepository.save(any())).thenReturn(item);
        when(mapper.toResponse(any())).thenReturn(itemResponse);

        ItemResponse result = service.alterarItem(id, itemUpdateRequest);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Teste para listar itens por hospedagem - sucesso")
    void testListarItensPorHospedagemSuccess() {
        long idHospedagem = 1L;
        when(hospedagemService.buscarHospedagemPorId(idHospedagem)).thenReturn(new HospedagemResponse());
        when(itemRepository.findByHospedagemId(idHospedagem)).thenReturn(Collections.singletonList(item));
        when(mapper.toResponses(Collections.singletonList(item))).thenReturn(Collections.singletonList(itemResponse));

        List<ItemResponse> result = service.listarItensPorHospedagem(idHospedagem);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Teste para buscar item por ID - sucesso")
    void testBuscarItemPorIdSuccess() {
        long id = 1L;
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));
        when(mapper.toResponse(any())).thenReturn(itemResponse);

        ItemResponse result = service.buscarItemPorId(id);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Teste para deletar item - sucesso")
    void testDeletarItemSuccess() {
        long id = 1L;
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        assertDoesNotThrow(() -> service.deletarItem(id));
        verify(itemRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Teste para salvar item - hospedagem não encontrada")
    void testSalvarItemHospedagemNotFound() {
        when(hospedagemService.buscarHospedagemPorId(anyLong())).thenThrow(new NotFoundException("Hospedagem não encontrada"));

        assertThrows(NotFoundException.class, () -> service.salvarItem(itemRequest));
    }

    @Test
    @DisplayName("Teste para atualizar item - falha")
    void testUpdateNotFound() {
        long id = 1L;
        when(itemRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.alterarItem(id, new ItemUpdateRequest()));

        verify(itemRepository, never()).save(any());
    }

}
