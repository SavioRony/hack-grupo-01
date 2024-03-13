package br.com.fiap.hackgrupo01.model.dto.opcionais;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequestId;
import lombok.Data;

@Data
public class ServicoResponse {
    private Long id;
    private String nome;
    private Double valor;
    private HospedagemRequestId hospedagem;
}
