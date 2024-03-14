package br.com.fiap.hackgrupo01.repository;

import br.com.fiap.hackgrupo01.model.reserva.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
