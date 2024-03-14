package br.com.fiap.hackgrupo01.model.dto.reserva;

import lombok.Data;

@Data
public class ItemReservaRequestDTO {

    private Integer quantidade;
    private ItemRequestDTO item;
}
