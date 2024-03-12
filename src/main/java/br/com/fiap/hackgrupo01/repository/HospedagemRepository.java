package br.com.fiap.hackgrupo01.repository;

import br.com.fiap.hackgrupo01.model.hospedagem.Hospedagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedagemRepository extends JpaRepository<Hospedagem, Long> {
}
