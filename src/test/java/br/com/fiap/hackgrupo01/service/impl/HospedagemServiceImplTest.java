package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.HospedagemMapper;
import br.com.fiap.hackgrupo01.mapper.PredioMapper;
import br.com.fiap.hackgrupo01.mapper.QuartoMapper;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.*;
import br.com.fiap.hackgrupo01.model.hospedagem.Hospedagem;
import br.com.fiap.hackgrupo01.model.hospedagem.Predio;
import br.com.fiap.hackgrupo01.model.hospedagem.Quarto;
import br.com.fiap.hackgrupo01.repository.HospedagemRepository;
import br.com.fiap.hackgrupo01.repository.PredioRepository;
import br.com.fiap.hackgrupo01.repository.QuartoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class HospedagemServiceImplTest {
    @Mock
    private HospedagemRepository repository;

    @Mock
    private QuartoRepository quartoRepository;

    @Mock
    private PredioRepository predioRepository;

    @Mock
    private HospedagemMapper hospedagemMapper;

    @Mock
    private PredioMapper predioMapper;

    @Mock
    private QuartoMapper quartoMapper;

    @InjectMocks
    private HospedagemServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Teste para cadastro de hospedagem")
    void testCadastroHospedagem() {
        HospedagemRequestDTO request = new HospedagemRequestDTO();
        Hospedagem hospedagem = new Hospedagem();
        when(hospedagemMapper.toModel(request)).thenReturn(hospedagem);
        when(repository.save(hospedagem)).thenReturn(hospedagem);
        HospedagemResponseDTO expectedResponse = new HospedagemResponseDTO();
        when(hospedagemMapper.toResponse(hospedagem)).thenReturn(expectedResponse);

        HospedagemResponseDTO response = service.salvarHospedagem(request);

        assertEquals(expectedResponse, response);
        verify(repository, times(1)).save(hospedagem);
    }

    @Test
    @DisplayName("Teste para cadastro de prédio")
    void testCadastroPredio() {
        long idHospedagem = 1L;
        PredioRequestDTO request = new PredioRequestDTO();
        HospedagemRequestIdDTO hospedagemRequestId = new HospedagemRequestIdDTO();
        hospedagemRequestId.setId(idHospedagem);
        request.setHospedagem(hospedagemRequestId);
        Hospedagem hospedagem = new Hospedagem();
        when(repository.findById(idHospedagem)).thenReturn(Optional.of(hospedagem)); // Alteração aqui
        Predio predio = new Predio();
        when(predioMapper.toModel(request)).thenReturn(predio);
        when(repository.save(hospedagem)).thenReturn(hospedagem);
        HospedagemResponseDTO expectedResponse = new HospedagemResponseDTO();
        when(hospedagemMapper.toResponse(hospedagem)).thenReturn(expectedResponse);

        HospedagemResponseDTO response = service.salvarPredio(request);

        assertEquals(expectedResponse, response);
        verify(repository, times(1)).save(hospedagem);
    }

    @Test
    @DisplayName("Teste para cadastro de quarto")
    void testCadastroQuarto() {
        long idPredio = 1L;
        QuartoRequestDTO request = new QuartoRequestDTO();
        PredioRequestIdDTO predioRequestId = new PredioRequestIdDTO();
        predioRequestId.setId(1L);
        request.setPredio(predioRequestId);
        Hospedagem hospedagem = new Hospedagem();
        Predio predio = new Predio();
        predio.setId(idPredio);
        List<Predio> predios = new ArrayList<>();
        predios.add(predio);
        hospedagem.setPredios(predios);
        when(repository.findByPredioId(idPredio)).thenReturn(Optional.of(hospedagem));
        when(quartoMapper.toModel(request)).thenReturn(new Quarto());
        when(repository.save(hospedagem)).thenReturn(hospedagem);
        HospedagemResponseDTO expectedResponse = new HospedagemResponseDTO();
        when(hospedagemMapper.toResponse(hospedagem)).thenReturn(expectedResponse);

        HospedagemResponseDTO response = service.salvarQuarto(request);

        assertEquals(expectedResponse, response);
        verify(repository, times(1)).save(hospedagem);
    }

    @Test
    @DisplayName("Teste para cadastro de quarto quando predio não possui o mesmo ID que o predio do request")
    void testCadastroQuartoPredioNotFoundInList() {
        long idPredioRequest = 1L;
        QuartoRequestDTO request = new QuartoRequestDTO();
        PredioRequestIdDTO predioRequestId = new PredioRequestIdDTO();
        predioRequestId.setId(idPredioRequest);
        request.setPredio(predioRequestId);

        long idPredioHospedagem = 2L;
        Predio predio = new Predio();
        predio.setId(idPredioHospedagem);

        Hospedagem hospedagem = new Hospedagem();
        List<Predio> predios = new ArrayList<>();
        predios.add(predio);
        hospedagem.setPredios(predios);

        when(repository.findByPredioId(idPredioRequest)).thenReturn(Optional.of(hospedagem));

        when(hospedagemMapper.toResponse(hospedagem)).thenReturn(null);

        HospedagemResponseDTO response = service.salvarQuarto(request);

        assertEquals(null, response);
        verify(repository, times(1)).save(hospedagem);
    }
    @Test
    @DisplayName("Teste para cadastro de prédio quando hospedagem não é encontrada")
    void testCadastroPredioHospedagemNotFound() {
        PredioRequestDTO request = new PredioRequestDTO();
        request.setHospedagem(new HospedagemRequestIdDTO());

        when(repository.getReferenceById(any())).thenThrow(EntityNotFoundException.class);

        assertThrows(NotFoundException.class, () -> service.salvarPredio(request));
    }

    @Test
    @DisplayName("Teste para cadastro de quarto quando prédio não é encontrado")
    void testCadastroQuartoPredioNotFound() {
        QuartoRequestDTO request = new QuartoRequestDTO();
        request.setPredio(new PredioRequestIdDTO());

        when(repository.findByPredioId(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.salvarQuarto(request));
    }

    @Test
    @DisplayName("Teste para obter todas as hospedagens")
    void testGetHospedagens() {
        List<Hospedagem> hospedagens = Collections.singletonList(new Hospedagem());
        when(repository.findAll()).thenReturn(hospedagens);

        List<HospedagemResponseDTO> expectedResponses = Collections.singletonList(new HospedagemResponseDTO());
        when(hospedagemMapper.toResponses(hospedagens)).thenReturn(expectedResponses);

        List<HospedagemResponseDTO> responses = service.buscarHospedagens();

        assertEquals(expectedResponses, responses);
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Teste para obter uma hospedagem por ID")
    void testGetHospedagemById() {
        long idHospedagem = 1L;
        Hospedagem hospedagem = new Hospedagem();
        when(repository.findById(idHospedagem)).thenReturn(Optional.of(hospedagem));

        HospedagemResponseDTO expectedResponse = new HospedagemResponseDTO();
        when(hospedagemMapper.toResponse(hospedagem)).thenReturn(expectedResponse);

        HospedagemResponseDTO response = service.buscarHospedagemPorId(idHospedagem);

        assertEquals(expectedResponse, response);
        verify(repository, times(1)).findById(idHospedagem);
    }

    @Test
    @DisplayName("Teste para obter uma hospedagem por ID do prédio")
    void testGetHospedagemByIdPredio() {
        long idPredio = 1L;
        Hospedagem hospedagem = new Hospedagem();
        when(repository.findByPredioId(idPredio)).thenReturn(Optional.of(hospedagem));

        HospedagemResponseDTO expectedResponse = new HospedagemResponseDTO();
        when(hospedagemMapper.toResponse(hospedagem)).thenReturn(expectedResponse);

        HospedagemResponseDTO response = service.buscarHospedagemPorIdPredio(idPredio);

        assertEquals(expectedResponse, response);
        verify(repository, times(1)).findByPredioId(idPredio);
    }

    @Test
    @DisplayName("Teste para obter uma hospedagem por ID do quarto")
    void testGetHospedagemByIdQuarto() {
        long idQuarto = 1L;
        Hospedagem hospedagem = new Hospedagem();
        when(repository.findByQuartoId(idQuarto)).thenReturn(Optional.of(hospedagem));

        HospedagemResponseDTO expectedResponse = new HospedagemResponseDTO();
        when(hospedagemMapper.toResponse(hospedagem)).thenReturn(expectedResponse);

        HospedagemResponseDTO response = service.buscarHospedagemPorIdQuarto(idQuarto);

        assertEquals(expectedResponse, response);
        verify(repository, times(1)).findByQuartoId(idQuarto);
    }

    @Test
    @DisplayName("Teste para obter uma hospedagem inexistente por ID")
    void testGetHospedagemByIdNotFound() {
        long idHospedagem = 1L;
        when(repository.findById(idHospedagem)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.buscarHospedagemPorId(idHospedagem));
    }

    @Test
    @DisplayName("Teste para obter uma hospedagem inexistente por ID do prédio")
    void testGetHospedagemByIdPredioNotFound() {
        long idPredio = 1L;
        when(repository.findByPredioId(idPredio)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.buscarHospedagemPorIdPredio(idPredio));
    }

    @Test
    @DisplayName("Teste para obter uma hospedagem inexistente por ID do quarto")
    void testGetHospedagemByIdQuartoNotFound() {
        long idQuarto = 1L;
        when(repository.findByQuartoId(idQuarto)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.buscarHospedagemPorIdQuarto(idQuarto));
    }

    @Test
    @DisplayName("Teste para alterar uma hospedagem")
    void testAlteracaoHospedagem() {
        long idHospedagem = 1L;
        HospedagemRequestDTO request = new HospedagemRequestDTO();
        Hospedagem hospedagem = new Hospedagem();
        when(repository.findById(idHospedagem)).thenReturn(Optional.of(hospedagem));
        when(repository.save(any())).thenReturn(hospedagem);

        service.alterarHospedagem(idHospedagem, request);

        verify(repository, times(1)).save(hospedagem);
    }

    @Test
    @DisplayName("Teste para alterar um prédio")
    void testAlteracaoPredio() {
        long idPredio = 1L;
        PredioUpdateRequestDTO request = new PredioUpdateRequestDTO();
        Hospedagem hospedagem = new Hospedagem();
        Predio predio = new Predio();
        predio.setId(idPredio);
        List<Predio> predios = new ArrayList<>();
        predios.add(predio);
        hospedagem.setPredios(predios);
        when(repository.findByPredioId(idPredio)).thenReturn(Optional.of(hospedagem));
        when(repository.save(any())).thenReturn(hospedagem);

        service.alterarPredio(idPredio, request);

        verify(repository, times(1)).save(hospedagem);
    }

    @Test
    @DisplayName("Teste para alterar um quarto")
    void testAlteracaoQuarto() {
        long idQuarto = 1L;
        QuartoUpdateRequestDTO request = new QuartoUpdateRequestDTO();
        Hospedagem hospedagem = new Hospedagem();
        when(repository.findByQuartoId(idQuarto)).thenReturn(Optional.of(hospedagem));
        when(repository.save(any())).thenReturn(hospedagem);

        service.alterarQuarto(idQuarto, request);

        verify(repository, times(1)).save(hospedagem);
    }
    @Test
    @DisplayName("Teste para excluir uma hospedagem por ID")
    void testExclusaoHospedagem() {
        long idHospedagem = 1L;
        Hospedagem hospedagem = new Hospedagem();
        when(repository.findById(idHospedagem)).thenReturn(Optional.of(hospedagem));

        service.deleteHospedagem(idHospedagem);

        verify(repository, times(1)).deleteById(idHospedagem);
    }

    @Test
    @DisplayName("Teste para excluir um prédio por ID")
    void testExclusaoPredio() {
        long idPredio = 1L;
        Hospedagem hospedagem = new Hospedagem();
        Predio predio = new Predio();
        predio.setId(idPredio);
        List<Predio> predios = new ArrayList<>();
        predios.add(predio);
        hospedagem.setPredios(predios);
        when(repository.findByPredioId(idPredio)).thenReturn(Optional.of(hospedagem));

        service.deletePredio(idPredio);

        verify(predioRepository, times(1)).deleteById(idPredio);
    }

    @Test
    @DisplayName("Teste para excluir um quarto por ID")
    void testExclusaoQuarto() {
        long idQuarto = 1L;
        Hospedagem hospedagem = new Hospedagem();
        when(repository.findByQuartoId(idQuarto)).thenReturn(Optional.of(hospedagem));

        service.deleteQuarto(idQuarto);

        verify(quartoRepository, times(1)).deleteById(idQuarto);
    }
    @Test
    @DisplayName("Teste para alteração de hospedagem quando não encontrada")
    void testAlteracaoHospedagemNotFound() {
        long idHospedagem = 1L;
        HospedagemRequestDTO request = new HospedagemRequestDTO();
        when(repository.findById(idHospedagem)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.alterarHospedagem(idHospedagem, request));
    }

    @Test
    @DisplayName("Teste para alteração de prédio quando hospedagem não encontrada ou id do prédio inválido")
    void testAlteracaoPredioNotFound() {
        long idPredio = 1L;
        PredioUpdateRequestDTO request = new PredioUpdateRequestDTO();
        when(repository.findByPredioId(idPredio)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.alterarPredio(idPredio, request));
    }

    @Test
    @DisplayName("Teste para alteração de quarto quando hospedagem não encontrada ou id do quarto inválido")
    void testAlteracaoQuartoNotFound() {
        long idQuarto = 1L;
        QuartoUpdateRequestDTO request = new QuartoUpdateRequestDTO();
        when(repository.findByQuartoId(idQuarto)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.alterarQuarto(idQuarto, request));
    }

    @Test
    @DisplayName("Teste para exclusão de hospedagem quando não encontrada")
    void testExclusaoHospedagemNotFound() {
        long idHospedagem = 1L;
        when(repository.findById(idHospedagem)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.deleteHospedagem(idHospedagem));
    }

    @Test
    @DisplayName("Teste para exclusão de prédio quando hospedagem não encontrada ou id do prédio inválido")
    void testExclusaoPredioNotFound() {
        long idPredio = 1L;
        when(repository.findByPredioId(idPredio)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.deletePredio(idPredio));
    }

    @Test
    @DisplayName("Teste para exclusão de quarto quando hospedagem não encontrada ou id do quarto inválido")
    void testExclusaoQuartoNotFound() {
        long idQuarto = 1L;
        when(repository.findByQuartoId(idQuarto)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.deleteQuarto(idQuarto));
    }

}