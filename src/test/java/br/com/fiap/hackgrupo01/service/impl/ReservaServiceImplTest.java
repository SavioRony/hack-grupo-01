package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.mapper.ReservaMapper;
import br.com.fiap.hackgrupo01.model.cliente.Cliente;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.*;
import br.com.fiap.hackgrupo01.model.hospedagem.Quarto;
import br.com.fiap.hackgrupo01.model.opcionais.Item;
import br.com.fiap.hackgrupo01.model.opcionais.Servico;
import br.com.fiap.hackgrupo01.model.reserva.Reserva;
import br.com.fiap.hackgrupo01.repository.ItemRepository;
import br.com.fiap.hackgrupo01.repository.QuartoRepository;
import br.com.fiap.hackgrupo01.repository.ReservaRepository;
import br.com.fiap.hackgrupo01.repository.ServicoRepository;
import br.com.fiap.hackgrupo01.service.ClienteService;
import br.com.fiap.hackgrupo01.service.HospedagemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ReservaServiceImplTest {

    @Mock
    private HospedagemService hospedagemService;

    @Mock
    private QuartoRepository quartoRepository;

    @Mock
    private ReservaMapper reservaMapper;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ClienteService clienteService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ServicoRepository servicoRepository;

    @Mock
    private EmailServiceImpl emailService;

    @InjectMocks
    private ReservaServiceImpl reservaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste para salvar reserva - sucesso")
    void testSalvarReservaSuccess() {
        ReservaRequestDTO reservaRequestDTO = new ReservaRequestDTO();
        reservaRequestDTO.setQuarto(new QuartoRequestDTO());
        reservaRequestDTO.setCliente(new ClienteRequestDTO());
        reservaRequestDTO.setEntrada(LocalDate.now());
        reservaRequestDTO.setSaida(LocalDate.of(2024, 03, 15));
        reservaRequestDTO.setQuantidadeHospedes(2);
        ItemReservaRequestDTO itemReservaRequestDTO = new ItemReservaRequestDTO();
        itemReservaRequestDTO.setItem(new ItemRequestDTO());
        itemReservaRequestDTO.setQuantidade(1);
        reservaRequestDTO.setItens(List.of(itemReservaRequestDTO));
        ServicoReservaRequestDTO servicoReservaRequestDTO = new ServicoReservaRequestDTO();
        servicoReservaRequestDTO.setServico(new ServicoRequestDTO());
        reservaRequestDTO.setServicos(List.of(servicoReservaRequestDTO));
        Reserva reservaMock = new Reserva();
        reservaMock.setCliente(new Cliente());
        ReservaResponseDTO reservaResponseDTO = new ReservaResponseDTO();
        when(reservaMapper.toModel(reservaRequestDTO)).thenReturn(reservaMock);
        when(reservaRepository.save(reservaMock)).thenReturn(reservaMock);
        when(reservaMapper.toResponse(reservaMock)).thenReturn(reservaResponseDTO);
        Quarto quarto = new Quarto();
        quarto.setTotalPessoas(3);
        quarto.setValorDiaria(3.0);
        when(quartoRepository.findById(any())).thenReturn(Optional.of(quarto));
        when(hospedagemService.buscarHospedagemPorIdQuarto(any())).thenReturn(new HospedagemResponseDTO());
        when(clienteService.buscarClientePorId(any())).thenReturn(new ClienteResponseDTO());
        Item item = new Item();
        item.setValor(3.0);
        when(itemRepository.findByIdAndHospedagemId(any(), any())).thenReturn(Optional.of(item));
        Servico servico = new Servico();
        servico.setValor(3.0);
        when(servicoRepository.findByIdAndHospedagemId(any(), any())).thenReturn(Optional.of(servico));

        ReservaResponseDTO result = reservaService.salvarReserva(reservaRequestDTO);

        assertNotNull(result);
        assertEquals(reservaResponseDTO, result);
        verify(reservaMapper, times(1)).toModel(reservaRequestDTO);
        verify(reservaRepository, times(1)).save(reservaMock);
        verify(reservaMapper, times(1)).toResponse(reservaMock);
        verify(emailService, times(1)).enviarConfirmacaoReserva(reservaMock);
    }

    @Test
    @DisplayName("Teste para salvar reserva - sucesso")
    void testAlterarReservaSuccess() {
        ReservaRequestUpdateDTO reservaRequestUpdateDTO = new ReservaRequestUpdateDTO();
        reservaRequestUpdateDTO.setQuarto(new QuartoRequestDTO());
        reservaRequestUpdateDTO.setEntrada(LocalDate.now());
        reservaRequestUpdateDTO.setSaida(LocalDate.of(2024, 03, 15));
        reservaRequestUpdateDTO.setQuantidadeHospedes(2);
        Reserva reservaMock = new Reserva();
        reservaMock.setCliente(new Cliente());
        reservaMock.setValorTotal(3.0);
        ReservaResponseDTO reservaResponseDTO = new ReservaResponseDTO();
        when(reservaMapper.toModel(reservaRequestUpdateDTO)).thenReturn(reservaMock);
        when(reservaRepository.save(reservaMock)).thenReturn(reservaMock);
        when(reservaMapper.toResponse(reservaMock)).thenReturn(reservaResponseDTO);
        Quarto quarto = new Quarto();
        quarto.setTotalPessoas(3);
        quarto.setValorDiaria(3.0);
        when(quartoRepository.findById(any())).thenReturn(Optional.of(quarto));
        when(hospedagemService.buscarHospedagemPorIdQuarto(any())).thenReturn(new HospedagemResponseDTO());
        when(clienteService.buscarClientePorId(any())).thenReturn(new ClienteResponseDTO());
        when(reservaRepository.findById(any())).thenReturn(Optional.of(new Reserva()));
        ReservaRequestDTO reservaRequestDTO = new ReservaRequestDTO();
        reservaRequestDTO.setQuarto(new QuartoRequestDTO());
        reservaRequestDTO.setEntrada(LocalDate.now());
        reservaRequestDTO.setSaida(LocalDate.of(2024, 03, 15));
        reservaRequestDTO.setQuantidadeHospedes(2);
        when(reservaMapper.toModel(reservaRequestDTO)).thenReturn(reservaMock);
        when(reservaMapper.toRequest(any())).thenReturn(reservaRequestDTO);

        ReservaResponseDTO result = reservaService.alterarReserva(reservaRequestUpdateDTO, 1L);

        assertNotNull(result);
        assertEquals(reservaResponseDTO, result);
        verify(reservaMapper, times(1)).toModel(reservaRequestDTO);
        verify(reservaRepository, times(1)).save(reservaMock);
        verify(reservaMapper, times(1)).toResponse(reservaMock);
        verify(emailService, times(1)).enviarConfirmacaoReserva(reservaMock);
    }

    @Test
    @DisplayName("Teste para buscar quartos disponíveis - sucesso")
    void testBuscarQuartosDisponiveisSuccess() {
        // Dados de entrada
        int quantidadeHospedes = 2;
        LocalDate entrada = LocalDate.now();
        LocalDate saida = LocalDate.now().plusDays(5);

        // Configuração dos mocks
        Quarto quartoDisponivel = new Quarto();
        when(quartoRepository.findQuartosDisponiveis(quantidadeHospedes)).thenReturn(Collections.singletonList(quartoDisponivel));
        when(reservaRepository.existsReservaConflitante(anyLong(), any(LocalDate.class), any(LocalDate.class), anyInt())).thenReturn(false);

        // Execução do método a ser testado
        List<Quarto> result = reservaService.buscarQuartosDisponiveis(quantidadeHospedes, entrada, saida);

        // Verificação
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(quartoDisponivel, result.get(0));
        verify(quartoRepository, times(1)).findQuartosDisponiveis(quantidadeHospedes);
    }

    @Test
    @DisplayName("Teste para buscar todas as reservas do cliente por e-mail - sucesso")
    void testBuscarTodasReservaDoClientePorEmailSuccess() {
        // Dados de entrada
        String email = "cliente@test.com";

        // Configuração dos mocks
        Reserva reserva = new Reserva();
        reserva.setCliente(new Cliente());
        reserva.getCliente().setEmail(email);
        when(reservaRepository.findAll()).thenReturn(Collections.singletonList(reserva));
        when(reservaMapper.toResponses(anyList())).thenReturn(Collections.singletonList(new ReservaResponseDTO()));

        // Execução do método a ser testado
        List<ReservaResponseDTO> result = reservaService.buscarTodasReservaDoClientePorEmail(email);

        // Verificação
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(reservaRepository, times(1)).findAll();
        verify(reservaMapper, times(1)).toResponses(anyList());
    }

    @Test
    @DisplayName("Teste para deletar reserva - sucesso")
    void testDeletarReservaSuccess() {
        // Dados de entrada
        Long id = 1L;
        Reserva reserva = new Reserva();
        reserva.setId(id);

        // Configuração dos mocks
        when(reservaRepository.findById(id)).thenReturn(Optional.of(reserva));

        // Execução do método a ser testado
        reservaService.deletarReserva(id);

        // Verificação
        verify(reservaRepository, times(1)).findById(id);
        verify(reservaRepository, times(1)).deleteById(id);
    }


}
