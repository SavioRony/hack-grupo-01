package br.com.fiap.hackgrupo01.repository;

import br.com.fiap.hackgrupo01.model.hospedagem.Hospedagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospedagemRepository extends JpaRepository<Hospedagem, Long> {

    @Query("SELECT h FROM Hospedagem h JOIN h.predios p WHERE p.id = :predioId")
    Optional<Hospedagem> findByPredioId(@Param("predioId") Long predioId);
}
