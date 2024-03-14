package br.com.fiap.hackgrupo01.model.dto.reserva;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuartoResponseIdDTO {

    private Long id;
}
