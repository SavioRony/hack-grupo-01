package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteDTO;
import br.com.fiap.hackgrupo01.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
@Tag(name = "Gestão de clientes", description = "Serviço de gerenciamento da clientes.")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    @Operation(summary = "Buscar todos os clientes")
    public ResponseEntity<List<ClienteDTO>> findAll(){
        var response = service.findAll();
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar clientes por id")
    public ResponseEntity<ClienteDTO> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Cadastro de Cliente")
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edição de Cliente")
    public ResponseEntity<ClienteDTO> update(@PathVariable(name = "id") Long id, @RequestBody ClienteDTO dto){
        return ResponseEntity.ok(service.update(dto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de Cliente")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
    }
}
