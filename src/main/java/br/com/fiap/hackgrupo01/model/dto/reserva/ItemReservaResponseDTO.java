package br.com.fiap.hackgrupo01.model.dto.reserva;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemResponse;
import lombok.Data;

@Data
public class ItemReservaResponseDTO {

    private Long id;
    private ItemResponse item;
    private Integer quantidade;
}
