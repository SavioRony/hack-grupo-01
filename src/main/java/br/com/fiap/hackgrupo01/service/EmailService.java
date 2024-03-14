package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.email.EmailRequestDTO;

public interface EmailService {
    void enviarConfirmacaoReserva(EmailRequestDTO emailRequest);
}
