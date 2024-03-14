package br.com.fiap.hackgrupo01.model.opcionais;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoUpdateRequestDTO;
import br.com.fiap.hackgrupo01.model.hospedagem.Hospedagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_servico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double valor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospedagem_id")
    private Hospedagem hospedagem;

    public void altera(ServicoUpdateRequestDTO servicoRequest){
        this.nome = servicoRequest.getNome();
        this.valor = servicoRequest.getValor();
    }
}