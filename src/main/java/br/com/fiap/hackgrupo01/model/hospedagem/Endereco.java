package br.com.fiap.hackgrupo01.model.hospedagem;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @NotBlank(message = "ruaAvenida não pode nulo ou vazio")
    private String ruaAvenida;
    @NotBlank(message = "cep não pode nulo ou vazio")
    private String cep;
    @NotBlank(message = "cidade não pode nulo ou vazio")
    private String cidade;
    @NotBlank(message = "estado não pode nulo ou vazio")
    private String estado;
}
