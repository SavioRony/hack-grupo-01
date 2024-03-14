package br.com.fiap.hackgrupo01.mapper;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemResponseDTO;
import br.com.fiap.hackgrupo01.model.opcionais.Item;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item toModel(ItemRequestDTO request);
    ItemResponseDTO toResponse(Item item);
    List<ItemResponseDTO> toResponses(List<Item> itens);
}
