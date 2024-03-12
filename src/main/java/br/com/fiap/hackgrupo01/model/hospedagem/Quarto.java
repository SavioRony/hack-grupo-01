package br.com.fiap.hackgrupo01.model.hospedagem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_quarto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private int totalPessoas;
    @ElementCollection
    private List<String> camas;
    @ElementCollection
    private List<String> outrosMoveis;
    @ManyToOne
    @JoinColumn(name = "predio_id")
    @JsonIgnore
    private Predio predio;
}
