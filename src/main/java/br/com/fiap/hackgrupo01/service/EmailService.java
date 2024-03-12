package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.email.EmailRequest;

public interface EmailService {
    void enviarConfirmacaoReserva(EmailRequest emailRequest);
}
