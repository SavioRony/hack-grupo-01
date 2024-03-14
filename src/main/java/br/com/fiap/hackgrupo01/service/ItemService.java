package br.com.fiap.hackgrupo01.service;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemUpdateRequestDTO;

import java.util.List;

public interface ItemService {
    ItemResponseDTO salvarItem(ItemRequestDTO item);
    ItemResponseDTO alterarItem(Long id, ItemUpdateRequestDTO item);
    List<ItemResponseDTO> listarItensPorHospedagem(Long idHospedagem);
    ItemResponseDTO buscarItemPorId(Long id);
    void deletarItem(Long id);
}