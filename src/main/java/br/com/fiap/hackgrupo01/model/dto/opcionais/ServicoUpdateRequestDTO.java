package br.com.fiap.hackgrupo01.model.dto.opcionais;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServicoUpdateRequestDTO {
    @NotBlank(message = "Nome não pode ser nulo ou vazio")
    private String nome;
    @NotNull(message = "Nome não pode ser nulo")
    private Double valor;
}
