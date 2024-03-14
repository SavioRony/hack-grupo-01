package br.com.fiap.hackgrupo01.model.dto.reserva;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReservaRequestDTO {

    @NotNull
    private ClienteReservaDTO cliente;

    private List<ItemReservaRequestDTO> itens;
    private List<ServicoReservaRequest> servicos;
    private QuartoRequestDTO quarto;

    @NotNull
    private LocalDate entrada;

    @NotNull
    private LocalDate saida;

    @NotNull
    private Integer quantidadeHospedes;
}
