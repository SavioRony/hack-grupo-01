package br.com.fiap.hackgrupo01.model.opcionais;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemUpdateRequestDTO;
import br.com.fiap.hackgrupo01.model.hospedagem.Hospedagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double valor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospedagem_id")
    private Hospedagem hospedagem;

    public void alterar(ItemUpdateRequestDTO request){
        this.nome = request.getNome();
        this.valor = request.getValor();
    }
}