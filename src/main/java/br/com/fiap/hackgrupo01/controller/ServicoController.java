package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoRequest;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoResponse;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoUpdateRequest;
import br.com.fiap.hackgrupo01.service.ServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;

    @PostMapping
    public ResponseEntity<ServicoResponse> salvarServico(@RequestBody @Valid ServicoRequest servico) {
        return new ResponseEntity<>(servicoService.salvarServico(servico), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponse> alterarServico(@PathVariable Long id, @RequestBody @Valid ServicoUpdateRequest servico) {
        return ResponseEntity.ok(servicoService.alterarServico(id,servico));
    }

    @GetMapping("/hospedagem/{idHospedagem}")
    public ResponseEntity<List<ServicoResponse>> listarServicosPorHospedagem(@PathVariable Long idHospedagem) {
        return ResponseEntity.ok(servicoService.listarServicosPorHospedagem(idHospedagem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponse> buscarServicoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicoService.buscarServicoPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable Long id) {
        servicoService.deletarServico(id);
        return ResponseEntity.noContent().build();
    }
}
