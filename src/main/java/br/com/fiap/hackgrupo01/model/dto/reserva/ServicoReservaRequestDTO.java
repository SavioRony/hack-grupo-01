package br.com.fiap.hackgrupo01.model.dto.reserva;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ServicoReservaRequestDTO {

    @Valid
    private ServicoRequestDTO servico;
}
