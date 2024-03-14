package br.com.fiap.hackgrupo01.model.dto.reserva;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServicoReservaRequest {

    @NotNull
    private Long id;
}
