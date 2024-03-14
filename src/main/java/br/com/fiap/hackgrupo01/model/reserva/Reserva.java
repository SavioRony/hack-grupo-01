package br.com.fiap.hackgrupo01.model.reserva;

import br.com.fiap.hackgrupo01.model.cliente.Cliente;
import br.com.fiap.hackgrupo01.model.hospedagem.Quarto;
import br.com.fiap.hackgrupo01.model.opcionais.Servico;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "tb_reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_reserva")
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "tb_reserva_qarto", joinColumns = @JoinColumn(name = "id_reserva"), inverseJoinColumns = @JoinColumn(name = "id_quarto"))
    private List<Quarto> quartos;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_reserva")
    List<ItemReserva> items;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_reserva")
    List<Servico> servicos;


    private LocalDate entrada;
    private LocalDate saida;
    private Integer quantidadeHospedes;

    private Double valorTotal;
}
