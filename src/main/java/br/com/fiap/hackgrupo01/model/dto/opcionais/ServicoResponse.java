package br.com.fiap.hackgrupo01.model.dto.opcionais;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequestId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServicoResponse {
    private Long id;
    private String nome;
    private Double valor;
    private HospedagemRequestId hospedagem;
}
