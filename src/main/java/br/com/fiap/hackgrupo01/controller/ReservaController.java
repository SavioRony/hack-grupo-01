package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.reserva.ReservaResponseDTO;
import br.com.fiap.hackgrupo01.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserva")
@Tag(name = "Gestão de reservas", description = "Serviço de gerenciamento da reservas.")
public class ReservaController {

    @Autowired
    protected ReservaService service;

    @GetMapping("/{email}")
    @Operation(summary = "Buscar todos as reservas por cliente")
    ResponseEntity<List<ReservaResponseDTO>> findAll(@PathVariable(name = "email") String email){
        return ResponseEntity.ok(service.findAll(email));
    }

    @PostMapping
    @Operation(summary = "Cadastro de Reserva")
    ResponseEntity<ReservaResponseDTO> create(@Valid @RequestBody ReservaRequestDTO request){
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edição de reserva")
    ResponseEntity<ReservaResponseDTO> update(@Valid @RequestBody ReservaRequestDTO request, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.update(request, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
    }
}
