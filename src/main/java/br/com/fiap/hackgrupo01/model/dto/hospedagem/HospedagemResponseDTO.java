package br.com.fiap.hackgrupo01.model.dto.hospedagem;

import br.com.fiap.hackgrupo01.model.hospedagem.Endereco;
import br.com.fiap.hackgrupo01.model.hospedagem.Predio;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HospedagemResponseDTO {
    private Long id;
    private String nome;
    private List<String> amenidades;
    private Endereco endereco;
    private List<Predio> predios;
}
