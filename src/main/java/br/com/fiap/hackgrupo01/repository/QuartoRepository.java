package br.com.fiap.hackgrupo01.repository;

import br.com.fiap.hackgrupo01.model.hospedagem.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    @Query("SELECT q FROM Quarto q WHERE q.totalPessoas >= :quantidadePessoas")
    List<Quarto> findQuartosDisponiveis(
            @Param("quantidadePessoas") int quantidadePessoas);
}
