package br.com.fiap.hackgrupo01.repository;

import br.com.fiap.hackgrupo01.model.hospedagem.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    @Query("SELECT q FROM Quarto q WHERE q.totalPessoas >= :quantidadePessoas " +
            "AND q.id NOT IN (" +
            "   SELECT r.quarto.id FROM Reserva r " +
            "   WHERE (:dataEntrada BETWEEN r.entrada AND r.saida " +
            "   OR :dataSaida BETWEEN r.entrada AND r.saida) " +
            "   OR (:dataEntrada <= r.entrada AND :dataSaida >= r.saida)" +
            ")")
    List<Quarto> findQuartosDisponiveis(
            @Param("quantidadePessoas") int quantidadePessoas,
            @Param("dataEntrada") LocalDate dataEntrada,
            @Param("dataSaida") LocalDate dataSaida);
}
