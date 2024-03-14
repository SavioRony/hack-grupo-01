package br.com.fiap.hackgrupo01.model.dto.reserva;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteReservaDTO {

    @NotBlank
    private Long id;
}
