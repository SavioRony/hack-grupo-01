package br.com.fiap.hackgrupo01.model.dto.reserva;

import lombok.Data;

import java.util.List;

@Data
public class QuartoResponseDTO {

    private Long id;
    private String tipo;
    private int totalPessoas;
    private List<String> camas;
    private List<String> outrosMoveis;
    private List<String> banheiro;
    private Double valorDiaria;
    private int quantidade;
}
