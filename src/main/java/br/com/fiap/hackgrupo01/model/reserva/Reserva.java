package br.com.fiap.hackgrupo01.model.reserva;

import br.com.fiap.hackgrupo01.model.cliente.Cliente;
import br.com.fiap.hackgrupo01.model.hospedagem.Quarto;
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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_quarto")
    private Quarto quarto;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reserva", referencedColumnName = "id")
    private List<ItemReserva> itens;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reserva", referencedColumnName = "id")
    private List<ServicoReserva> servicos;


    private LocalDate entrada;
    private LocalDate saida;
    private Integer quantidadeHospedes;
    private Double valorTotal;

    public void update(Reserva model) {
        this.setId(model.getId());
        this.setCliente(model.getCliente());
    }
}
