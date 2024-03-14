package br.com.fiap.hackgrupo01.repository;

import br.com.fiap.hackgrupo01.model.opcionais.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findByHospedagemId(Long hospedagemId);

    Optional<Servico> findByIdAndHospedagemId(Long id, Long idHospedagem);
}
