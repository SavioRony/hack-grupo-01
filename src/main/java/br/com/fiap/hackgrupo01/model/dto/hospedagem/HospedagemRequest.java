package br.com.fiap.hackgrupo01.model.dto.hospedagem;

import br.com.fiap.hackgrupo01.model.hospedagem.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class HospedagemRequest {
    @NotBlank(message = "Nome não pode nulo ou vazio")
    private String nome;
    private List<String> amenidades;
    @NotNull(message = "Endereço não pode ser nulo")
    private Endereco endereco;
}
