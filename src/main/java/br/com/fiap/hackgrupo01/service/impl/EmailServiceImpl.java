package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.model.reserva.Reserva;
import br.com.fiap.hackgrupo01.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Value("${send.mail.from}")
    private String from;

    private final JavaMailSender emailSender;

    public void enviarConfirmacaoReserva(Reserva reserva) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(from);
            helper.setTo(reserva.getCliente().getEmail());
            helper.setSubject("Confirmação de Reserva de Hospedagem");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String htmlContent = carregarConteudoHTML("templates/confirmacao_reserva.html");
            htmlContent = htmlContent.replace("{nome_do_hospede}", reserva.getCliente().getNomeCompleto());
            htmlContent = htmlContent.replace("{email_do_hospede}", reserva.getCliente().getEmail());
            htmlContent = htmlContent.replace("{data_check_in}", reserva.getEntrada().format(formatter));
            htmlContent = htmlContent.replace("{data_check_out}", reserva.getSaida().format(formatter));
            htmlContent = htmlContent.replace("{tipo_de_quarto}", reserva.getQuarto().getTipo());
            htmlContent = htmlContent.replace("{numero_de_hospedes}", Integer.toString(reserva.getQuantidadeHospedes()));
            htmlContent = htmlContent.replace("{valor_total}", Double.toString(reserva.getValorTotal()));

            helper.setText(htmlContent, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage());
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
            log.error(e.getMessage());
        }
        return contentBuilder.toString();
    }
}
