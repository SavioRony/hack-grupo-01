package br.com.fiap.hackgrupo01.repository;

import br.com.fiap.hackgrupo01.model.hospedagem.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {

}
