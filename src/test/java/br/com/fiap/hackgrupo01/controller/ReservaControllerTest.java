package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaRequestUpdateDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.ClienteRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.ItemReservaRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.ServicoReservaRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.QuartoRequestDTO;
import br.com.fiap.hackgrupo01.model.hospedagem.Quarto;
import br.com.fiap.hackgrupo01.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReservaControllerTest {

    @Mock
    private ReservaService reservaService;

    @InjectMocks
    private ReservaController reservaController;

    private ReservaResponseDTO reservaResponse;
    private ReservaRequestDTO reservaRequest;
    private ReservaRequestUpdateDTO reservaRequestUpdate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        reservaResponse = new ReservaResponseDTO();
        reservaResponse.setId(1L);
        reservaResponse.setEntrada(LocalDate.now());
        reservaResponse.setSaida(LocalDate.now().plusDays(5));
        reservaResponse.setQuantidadeHospedes(2);
        reservaResponse.setValorTotal(100.00);

        reservaRequest = new ReservaRequestDTO();
        reservaRequest.setCliente(new ClienteRequestDTO());
        reservaRequest.setItens(Collections.singletonList(new ItemReservaRequestDTO()));
        reservaRequest.setServicos(Collections.singletonList(new ServicoReservaRequestDTO()));
        reservaRequest.setQuarto(new QuartoRequestDTO());
        reservaRequest.setEntrada(LocalDate.now());
        reservaRequest.setSaida(LocalDate.now().plusDays(5));
        reservaRequest.setQuantidadeHospedes(2);

        reservaRequestUpdate = new ReservaRequestUpdateDTO();
    }
    @Test
    @DisplayName("Teste para buscar todas as reservas do cliente por email")
    void testFindAll() {
        String email = "fulano@example.com";
        when(reservaService.buscarTodasReservaDoClientePorEmail(email)).thenReturn(Collections.singletonList(reservaResponse));

        ResponseEntity<List<ReservaResponseDTO>> responseEntity = reservaController.findAll(email);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.singletonList(reservaResponse), responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para cadastrar reserva")
    void testCreate() {
        when(reservaService.salvarReserva(reservaRequest)).thenReturn(reservaResponse);

        ResponseEntity<ReservaResponseDTO> responseEntity = reservaController.create(reservaRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(reservaResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para atualizar reserva")
    void testUpdate() {
        Long id = 1L;
        when(reservaService.alterarReserva(reservaRequestUpdate, id)).thenReturn(reservaResponse);

        ResponseEntity<ReservaResponseDTO> responseEntity = reservaController.update(reservaRequestUpdate, id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reservaResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste para deletar reserva")
    void testDelete() {
        Long id = 1L;
        ResponseEntity<Void> responseEntity = reservaController.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(reservaService, times(1)).deletarReserva(id);
    }

    @Test
    @DisplayName("Teste para buscar quartos disponíveis")
    void testFindAvailableRooms() {
        int quantidade = 2;
        LocalDate entrada = LocalDate.now();
        LocalDate saida = entrada.plusDays(5);
        List<Quarto> quartosDisponiveis = Collections.singletonList(new Quarto());
        when(reservaService.buscarQuartosDisponiveis(quantidade, entrada, saida)).thenReturn(quartosDisponiveis);

        ResponseEntity<List<Quarto>> responseEntity = reservaController.findAll(quantidade, entrada, saida);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(quartosDisponiveis, responseEntity.getBody());
    }
}