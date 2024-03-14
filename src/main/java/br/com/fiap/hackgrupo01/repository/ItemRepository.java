package br.com.fiap.hackgrupo01.repository;

import br.com.fiap.hackgrupo01.model.opcionais.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByHospedagemId(Long hospedagemId);

    Optional<Item> findByIdAndHospedagemId(Long itemId, Long hospedagemId);

}
