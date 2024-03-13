package br.com.fiap.hackgrupo01.mapper;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemRequest;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemResponse;
import br.com.fiap.hackgrupo01.model.opcionais.Item;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item toModel(ItemRequest request);
    ItemResponse toResponse(Item item);
    List<ItemResponse> toResponses(List<Item> itens);
}
