package br.com.fiap.hackgrupo01.controller;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemRequest;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemResponse;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ItemUpdateRequest;
import br.com.fiap.hackgrupo01.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Tag(name = "Gestão de Serviços e Opcionais", description = "Gerenciamento de Serviços e Opcionais")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    @Operation(summary = "Cadastro de item")
    public ResponseEntity<ItemResponse> salvarItem(@RequestBody @Valid ItemRequest item) {
        return new ResponseEntity<>(itemService.salvarItem(item), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar item")
    public ResponseEntity<ItemResponse> alterarItem(@PathVariable Long id, @RequestBody @Valid ItemUpdateRequest item) {
        return ResponseEntity.ok(itemService.alterarItem(id, item));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar item por ID")
    public ResponseEntity<ItemResponse> buscarItemPorId(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.buscarItemPorId(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar item por ID")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id) {
        itemService.deletarItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hospedagem/{idHospedagem}")
    @Operation(summary = "Buscar itens por hospedagem")
    public ResponseEntity<List<ItemResponse>> listarItens(@PathVariable Long idHospedagem) {
        return ResponseEntity.ok(itemService.listarItensPorHospedagem(idHospedagem));
    }
}
