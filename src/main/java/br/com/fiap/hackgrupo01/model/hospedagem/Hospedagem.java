package br.com.fiap.hackgrupo01.model.hospedagem;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.PredioUpdateRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.QuartoUpdateRequestDTO;
import br.com.fiap.hackgrupo01.model.opcionais.Item;
import br.com.fiap.hackgrupo01.model.opcionais.Servico;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "hospedagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Servico> servicos;

    @JsonIgnore
    @OneToMany(mappedBy = "hospedagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> itens;
    public void alteracaoHospedagem(HospedagemRequestDTO request) {
        this.nome = request.getNome();
        this.amenidades = request.getAmenidades();
        this.endereco = request.getEndereco();
    }

    public void alteracaoPredio(Long idPredio, PredioUpdateRequestDTO request) {
        for(Predio predio : this.getPredios()){
            if(predio.getId().equals(idPredio)){
                predio.setNome(request.getNome());
                break;
            }
        }
    }

    public void alteracaoQuarto(Long idQuarto, QuartoUpdateRequestDTO request) {
        for(Predio predio : this.getPredios()){
            for(Quarto quarto : predio.getQuartos()){
                if(quarto.getId().equals(idQuarto)){
                    quarto.setCamas(request.getCamas());
                    quarto.setTipo(request.getTipo());
                    quarto.setOutrosMoveis(request.getOutrosMoveis());
                    quarto.setTotalPessoas(request.getTotalPessoas());
                    quarto.setBanheiro(request.getBanheiro());
                    quarto.setQuantidade(request.getQuantidade());
                    quarto.setValorDiaria(request.getValorDiaria());
                    return;
                }
            }

        }
    }
}
