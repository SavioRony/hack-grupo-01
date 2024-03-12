package br.com.fiap.hackgrupo01.model.dto.hospedagem;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PredioRequestId {
    @NotNull(message = "Id do predio não pode ser nulo.")
    private Long id;
}
