package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.model.dto.email.EmailRequest;
import br.com.fiap.hackgrupo01.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${send.mail.from}")
    private String from;

    @Autowired
    private JavaMailSender emailSender;

    public void enviarConfirmacaoReserva(EmailRequest emailRequest) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(from);
            helper.setTo(emailRequest.emailHospede());
            helper.setSubject("Confirmação de Reserva de Hospedagem");

            String htmlContent = carregarConteudoHTML("templates/confirmacao_reserva.html");

            htmlContent = htmlContent.replace("{nome_do_hospede}", emailRequest.nomeHospede());
            htmlContent = htmlContent.replace("{email_do_hospede}", emailRequest.emailHospede());
            htmlContent = htmlContent.replace("{data_check_in}", emailRequest.dataCheckIn());
            htmlContent = htmlContent.replace("{data_check_out}", emailRequest.dataCheckOut());
            htmlContent = htmlContent.replace("{tipo_de_quarto}", emailRequest.tipoQuarto());
            htmlContent = htmlContent.replace("{numero_de_hospedes}", Integer.toString(emailRequest.numeroHospedes()));

            helper.setText(htmlContent, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String carregarConteudoHTML(String nomeArquivo) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            InputStream inputStream = new ClassPathResource(nomeArquivo).getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
