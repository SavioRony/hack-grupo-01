package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.ItemMapper;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequestIdDTO;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemUpdateRequestDTO;
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

    private ItemRequestDTO itemRequest;
    private ItemUpdateRequestDTO itemUpdateRequest;
    private Item item;
    private ItemResponseDTO itemResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        itemRequest = new ItemRequestDTO();
        HospedagemRequestIdDTO hospedagemRequestId = new HospedagemRequestIdDTO();
        hospedagemRequestId.setId(1L);
        itemRequest.setHospedagem(hospedagemRequestId);
        itemUpdateRequest = new ItemUpdateRequestDTO();
        item = new Item();
        itemResponse = new ItemResponseDTO();
    }

    @Test
    @DisplayName("Teste para salvar item - sucesso")
    void testSalvarItemSuccess() {
        when(hospedagemService.buscarHospedagemPorId(anyLong())).thenReturn(new HospedagemResponseDTO());
        when(itemRepository.save(any())).thenReturn(item);
        when(mapper.toResponse(any())).thenReturn(itemResponse);

        ItemResponseDTO result = service.salvarItem(itemRequest);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Teste para alterar item - sucesso")
    void testAlterarItemSuccess() {
        long id = 1L;
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));
        when(itemRepository.save(any())).thenReturn(item);
        when(mapper.toResponse(any())).thenReturn(itemResponse);

        ItemResponseDTO result = service.alterarItem(id, itemUpdateRequest);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Teste para listar itens por hospedagem - sucesso")
    void testListarItensPorHospedagemSuccess() {
        long idHospedagem = 1L;
        when(hospedagemService.buscarHospedagemPorId(idHospedagem)).thenReturn(new HospedagemResponseDTO());
        when(itemRepository.findByHospedagemId(idHospedagem)).thenReturn(Collections.singletonList(item));
        when(mapper.toResponses(Collections.singletonList(item))).thenReturn(Collections.singletonList(itemResponse));

        List<ItemResponseDTO> result = service.listarItensPorHospedagem(idHospedagem);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Teste para buscar item por ID - sucesso")
    void testBuscarItemPorIdSuccess() {
        long id = 1L;
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));
        when(mapper.toResponse(any())).thenReturn(itemResponse);

        ItemResponseDTO result = service.buscarItemPorId(id);

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

        assertThrows(NotFoundException.class, () -> service.alterarItem(id, new ItemUpdateRequestDTO()));

        verify(itemRepository, never()).save(any());
    }

}
