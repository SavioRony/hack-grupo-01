package br.com.fiap.hackgrupo01.model.reserva;

import br.com.fiap.hackgrupo01.model.opcionais.Item;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_reserva_itens")
public class ItemReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_item")
    private Item item;

    private Integer quantidade;
}
