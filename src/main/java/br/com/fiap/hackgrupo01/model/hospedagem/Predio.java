package br.com.fiap.hackgrupo01.model.hospedagem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_predio")
public class Predio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "predio", cascade = CascadeType.ALL)
    private List<Quarto> quartos;
    @ManyToOne
    @JoinColumn(name = "hospedagem_id")
    private Hospedagem hospedagem;
}
