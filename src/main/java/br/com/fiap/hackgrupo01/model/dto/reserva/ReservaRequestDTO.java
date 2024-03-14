package br.com.fiap.hackgrupo01.model.dto.reserva;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReservaRequestDTO {

    @NotNull
    private ClienteReservaDTO cliente;

    List<ItemReservaRequestDTO> items;
    List<ServicoReservaRequest> servicos;
    private List<QuartoRequestDTO> quartos;

    @NotNull
    private LocalDate entrada;

    @NotNull
    private LocalDate saida;

    @NotNull
    private Integer quantidadeHospedes;
}
