package br.com.fiap.hackgrupo01.service.impl;

import br.com.fiap.hackgrupo01.exception.NotFoundException;
import br.com.fiap.hackgrupo01.mapper.ItemMapper;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemUpdateRequestDTO;
import br.com.fiap.hackgrupo01.model.opcionais.Item;
import br.com.fiap.hackgrupo01.repository.ItemRepository;
import br.com.fiap.hackgrupo01.service.HospedagemService;
import br.com.fiap.hackgrupo01.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final HospedagemService hospedagemService;
    private final ItemRepository itemRepository;
    private final ItemMapper mapper;

    @Override
    public ItemResponseDTO salvarItem(ItemRequestDTO item) {
        hospedagemService.buscarHospedagemPorId(item.getHospedagem().getId());
        Item savedItem = itemRepository.save(mapper.toModel(item));
        return mapper.toResponse(savedItem);
    }

    @Override
    public ItemResponseDTO alterarItem(Long id, ItemUpdateRequestDTO request) {
        Item item = findById(id);
        item.alterar(request);
        return mapper.toResponse(itemRepository.save(item));
    }

    @Override
    public List<ItemResponseDTO> listarItensPorHospedagem(Long idHospedagem) {
        hospedagemService.buscarHospedagemPorId(idHospedagem);
        List<Item> itens = itemRepository.findByHospedagemId(idHospedagem);
        return mapper.toResponses(itens);
    }

    @Override
    public ItemResponseDTO buscarItemPorId(Long id) {
        Item item = findById(id);
        return mapper.toResponse(item);
    }

    @Override
    public void deletarItem(Long id) {
        buscarItemPorId(id);
        itemRepository.deleteById(id);
    }

    private Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new NotFoundException("Item não encontrado"));
    }
}