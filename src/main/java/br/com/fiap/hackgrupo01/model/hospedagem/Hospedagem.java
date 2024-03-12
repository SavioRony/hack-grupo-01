package br.com.fiap.hackgrupo01.model.hospedagem;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequest;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.PredioUpdateRequest;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.QuartoUpdateRequest;
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

    public void alteracaoHospedagem(HospedagemRequest request) {
        this.nome = request.getNome();
        this.amenidades = request.getAmenidades();
        this.endereco = request.getEndereco();
    }

    public void alteracaoPredio(Long idPredio, PredioUpdateRequest request) {
        for(Predio predio : this.getPredios()){
            if(predio.getId().equals(idPredio)){
                predio.setNome(request.getNome());
                break;
            }
        }
    }

    public void alteracaoQuarto(Long idQuarto, QuartoUpdateRequest request) {
        for(Predio predio : this.getPredios()){
            for(Quarto quarto : predio.getQuartos()){
                if(quarto.getId().equals(idQuarto)){
                    quarto.setCamas(request.getCamas());
                    quarto.setTipo(request.getTipo());
                    quarto.setOutrosMoveis(request.getOutrosMoveis());
                    quarto.setTotalPessoas(request.getTotalPessoas());
                    break;
                }
            }

        }
    }
}
