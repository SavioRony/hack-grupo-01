package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.reserva.Reserva;

public interface EmailService {
    void enviarConfirmacaoReserva(Reserva reserva);
}
