package br.com.fiap.hackgrupo01.model.dto.reserva;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservaResponseDTO {

    private ClienteReservaDTO cliente;
    private List<ItemReservaResponseDTO> itens;
    private List<ServicoReservaResponseDTO> servicos;
    private QuartoResponseIdDTO quarto;
    private LocalDate entrada;
    private LocalDate saida;
    private Integer quantidadeHospedes;
    private Double valorTotal;
}
