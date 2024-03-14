package br.com.fiap.hackgrupo01.model.dto.email;

public record EmailRequestDTO(String nomeHospede, String emailHospede, String dataCheckIn, String dataCheckOut, String tipoQuarto, int numeroHospedes) {
}
