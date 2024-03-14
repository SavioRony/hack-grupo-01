package br.com.fiap.hackgrupo01.model.dto.hospedagem;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PredioRequestDTO {
    @NotBlank(message = "Nome não pode ser nulo ou vazio.")
    private String nome;
    @Valid
    private HospedagemRequestIdDTO hospedagem;
}
