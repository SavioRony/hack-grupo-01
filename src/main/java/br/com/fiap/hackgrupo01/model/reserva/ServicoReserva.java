package br.com.fiap.hackgrupo01.model.reserva;

import br.com.fiap.hackgrupo01.model.opcionais.Servico;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_reserva_servicos")
public class ServicoReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_servico")
    private Servico servico;
}
