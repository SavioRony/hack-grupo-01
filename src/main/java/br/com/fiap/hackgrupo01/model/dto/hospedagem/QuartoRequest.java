package br.com.fiap.hackgrupo01.model.dto.hospedagem;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class QuartoRequest {
    @NotBlank(message = "Não pode ser nulo ou vazio")
    private String tipo;
    @Size(min = 1, message = "Quantidade minima de pessoas permitido é 1")
    private int totalPessoas;
    @NotBlank(message = "Não pode ser nulo ou vazio")
    private List<String> camas;
    private List<String> outrosMoveis;
    @Valid
    private PredioRequestId predio;
}
