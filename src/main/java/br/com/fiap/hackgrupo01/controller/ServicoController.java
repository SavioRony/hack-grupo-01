package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoRequestDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoResponseDTO;
import br.com.fiap.hackgrupo01.model.dto.opcionais.ServicoUpdateRequestDTO;
import br.com.fiap.hackgrupo01.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
@RequiredArgsConstructor
@Tag(name = "Gestão de Serviços e Opcionais", description = "Gerenciamento de Serviços e Opcionais")
public class ServicoController {

    private final ServicoService servicoService;

    @PostMapping
    @Operation(summary = "Cadastro de serviço")
    public ResponseEntity<ServicoResponseDTO> salvarServico(@RequestBody @Valid ServicoRequestDTO servico) {
        return new ResponseEntity<>(servicoService.salvarServico(servico), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar de serviço")
    public ResponseEntity<ServicoResponseDTO> alterarServico(@PathVariable Long id, @RequestBody @Valid ServicoUpdateRequestDTO servico) {
        return ResponseEntity.ok(servicoService.alterarServico(id,servico));
    }

    @GetMapping("/hospedagem/{idHospedagem}")
    @Operation(summary = "Buscar serviço por hospedagem")
    public ResponseEntity<List<ServicoResponseDTO>> listarServicosPorHospedagem(@PathVariable Long idHospedagem) {
        return ResponseEntity.ok(servicoService.listarServicosPorHospedagem(idHospedagem));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar serviço por id")
    public ResponseEntity<ServicoResponseDTO> buscarServicoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicoService.buscarServicoPorId(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar serviço por id")
    public ResponseEntity<Void> deletarServico(@PathVariable Long id) {
        servicoService.deletarServico(id);
        return ResponseEntity.noContent().build();
    }
}
