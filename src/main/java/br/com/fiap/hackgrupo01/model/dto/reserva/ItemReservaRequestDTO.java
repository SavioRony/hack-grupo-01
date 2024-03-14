package br.com.fiap.hackgrupo01.model.dto.reserva;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemReservaRequestDTO {


    private Long id;
    @NotNull
    private Integer quantidade;
    @NotNull
    private ItemReservaRefreshDTO item;
}
