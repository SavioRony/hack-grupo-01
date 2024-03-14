package br.com.fiap.hackgrupo01.repository;

import br.com.fiap.hackgrupo01.model.reserva.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("SELECT COUNT(r) >= :quantidadeTotalQuartos FROM Reserva r " +
            "WHERE r.quarto.id = :quartoId " +
            "AND ((r.entrada BETWEEN :dataEntrada AND :dataSaida) OR " +
            "     (r.saida BETWEEN :dataEntrada AND :dataSaida) OR " +
            "     (:dataEntrada BETWEEN r.entrada AND r.saida) OR " +
            "     (:dataSaida BETWEEN r.entrada AND r.saida))")
    boolean existsReservaConflitante(@Param("quartoId") Long quartoId,
                                     @Param("dataEntrada") LocalDate dataEntrada,
                                     @Param("dataSaida") LocalDate dataSaida,
                                     @Param("quantidadeTotalQuartos") int quantidadeTotalQuartos);
}
