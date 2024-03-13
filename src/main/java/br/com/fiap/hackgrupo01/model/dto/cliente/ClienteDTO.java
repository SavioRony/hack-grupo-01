package br.com.fiap.hackgrupo01.model.dto.cliente;

import br.com.fiap.hackgrupo01.anotations.ValidCPF;
import br.com.fiap.hackgrupo01.anotations.ValidPassaporte;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id;

    @NotBlank
    private String paisOrigem;

    @CPF
    @ValidCPF
    @Column(unique = true)
    private String cpf;

    @ValidPassaporte
    private String passaporte;

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    @Pattern(regexp="\\d{1,2}\\/\\d{1,2}\\/\\d{4}", message="Deve seguir o padrão ##/##/####")
    private String dataNascimento;

    @NotBlank
    private String enderecoPaisOrigem;

    @NotBlank
    private String telefone;

    @NotBlank
    private String email;
}
