package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.EmailRequest;
import br.com.fiap.hackgrupo01.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailTestController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){
        emailService.enviarConfirmacaoReserva(emailRequest);
        return ResponseEntity.noContent().build();
    }

}
