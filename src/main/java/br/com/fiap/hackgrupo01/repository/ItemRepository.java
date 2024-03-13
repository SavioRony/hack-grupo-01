package br.com.fiap.hackgrupo01.repository;

import br.com.fiap.hackgrupo01.model.opcionais.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByHospedagemId(Long hospedagemId);
}
