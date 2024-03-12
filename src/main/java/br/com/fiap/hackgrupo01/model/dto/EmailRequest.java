package br.com.fiap.hackgrupo01.model.dto;

public record EmailRequest(String nomeHospede, String emailHospede, String dataCheckIn, String dataCheckOut, String tipoQuarto, int numeroHospedes) {
}
