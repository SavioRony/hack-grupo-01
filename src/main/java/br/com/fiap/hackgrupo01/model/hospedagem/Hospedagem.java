package br.com.fiap.hackgrupo01.model.hospedagem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "tb_hospedagem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hospedagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ElementCollection
    private List<String> amenidades = new ArrayList<>();
    @Embedded
    private Endereco endereco;
    @OneToMany(mappedBy = "hospedagem", cascade = CascadeType.ALL)
    private List<Predio> predios = new ArrayList<>();

}
