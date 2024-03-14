package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteRequest;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteResponse;
import br.com.fiap.hackgrupo01.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fiap.hackgrupo01.validations.Validator.validateFields;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
@Tag(name = "Gestão de clientes", description = "Serviço de gerenciamento da clientes.")
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    @Operation(summary = "Buscar todos os clientes")
    public ResponseEntity<List<ClienteResponse>> findAll(){
        var response = service.buscarClientes();
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar clientes por id")
    public ResponseEntity<ClienteResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.buscarClientePorId(id));
    }

    @PostMapping
    @Operation(summary = "Cadastro de Cliente")
    public ResponseEntity<ClienteResponse> create(@Valid @RequestBody ClienteRequest dto){
        validateFields(dto);
        return ResponseEntity.ok(service.salvarCliente(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edição de Cliente")
    public ResponseEntity<ClienteResponse> update(@Valid @PathVariable(name = "id") Long id, @RequestBody ClienteRequest dto){
        validateFields(dto);
        return ResponseEntity.ok(service.alterarCliente(dto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de Cliente")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        service.deletarCliente(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
    }
}
