package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemRequest;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemResponse;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemUpdateRequest;

import java.util.List;

public interface ItemService {
    ItemResponse salvarItem(ItemRequest item);
    ItemResponse alterarItem(Long id, ItemUpdateRequest item);
    List<ItemResponse> listarItensPorHospedagem(Long idHospedagem);
    ItemResponse buscarItemPorId(Long id);
    void deletarItem(Long id);
}