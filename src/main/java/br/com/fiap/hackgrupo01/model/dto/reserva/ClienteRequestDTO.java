package br.com.fiap.hackgrupo01.model.dto.reserva;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteRequestDTO {

    @NotBlank
    private Long id;
}
