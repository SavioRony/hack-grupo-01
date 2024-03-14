package br.com.fiap.hackgrupo01.model.dto.reserva;

import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoResponse;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReservaResponseDTO {

    private ClienteDTO cliente;
    List<ItemReservaResponseDTO> itens;
    List<ServicoResponse> servicos;
    private QuartoResponseDTO quarto;
    private LocalDate entrada;
    private LocalDate saida;
    private Integer quantidadeHospedes;
    private Double valorTotal;
}
