package br.com.fiap.hackgrupo01.model.dto.hospedagem;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class QuartoRequest {
    @NotBlank(message = "Não pode ser nulo ou vazio")
    private String tipo;
    @Min(value = 1, message = "Quantidade minima de pessoas permitido é 1")
    private int totalPessoas;
    @NotNull(message = "A lista de camas não pode ser nula")
    @NotEmpty(message = "A lista de camas não pode ser vazia")
    private List<String> camas;
    private List<String> outrosMoveis;
    private List<String> banheiro;
    private Double valorDiaria;
    private int quantidade;
    @Valid
    private PredioRequestId predio;
}
