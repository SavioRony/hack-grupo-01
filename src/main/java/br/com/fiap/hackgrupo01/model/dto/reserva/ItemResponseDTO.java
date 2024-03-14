package br.com.fiap.hackgrupo01.model.dto.reserva;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemResponseDTO {

    private Long id;
    private String nome;
    private double valor;
}
