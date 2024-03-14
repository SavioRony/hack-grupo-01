package br.com.fiap.hackgrupo01.model.dto.reserva;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReservaRequestUpdateDTO {

    private List<ItemReservaRequestDTO> itens;
    private List<ServicoReservaRequestDTO> servicos;
    private QuartoRequestDTO quarto;

    @NotNull
    private LocalDate entrada;

    @NotNull
    private LocalDate saida;

    @NotNull
    private Integer quantidadeHospedes;
}
