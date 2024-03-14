package br.com.fiap.hackgrupo01.model.dto.hospedagem;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HospedagemRequestIdDTO {
    @NotNull(message = "id da hospedagem não pode ser nulo.")
    private Long id;
}
