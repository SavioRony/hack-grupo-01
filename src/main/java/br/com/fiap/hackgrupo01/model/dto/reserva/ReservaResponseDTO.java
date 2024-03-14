package br.com.fiap.hackgrupo01.model.dto.reserva;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservaResponseDTO {

    private Long id;
    private LocalDate entrada;
    private LocalDate saida;
    private Integer quantidadeHospedes;
    private Double valorTotal;
}
