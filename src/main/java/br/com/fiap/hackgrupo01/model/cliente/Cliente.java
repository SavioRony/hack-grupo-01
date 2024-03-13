package br.com.fiap.hackgrupo01.model.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paisOrigem;

    @Column(unique = true)
    private String cpf;
    private String passaporte;
    private String nomeCompleto;
    private String dataNascimento;
    private String enderecoPaisOrigem;
    private String telefone;
    private String email;
}
