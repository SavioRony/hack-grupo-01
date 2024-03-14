package br.com.fiap.hackgrupo01.model.hospedagem;

import br.com.fiap.hackgrupo01.model.reserva.Reserva;
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
    @ElementCollection
    private List<String> banheiro;
    private Double valorDiaria;
    private int quantidade;
    @ManyToOne
    @JoinColumn(name = "predio_id")
    @JsonIgnore
    private Predio predio;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "quartos")
    private List<Reserva> reserva;
}
