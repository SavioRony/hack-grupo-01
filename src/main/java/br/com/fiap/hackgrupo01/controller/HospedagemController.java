package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.*;
import br.com.fiap.hackgrupo01.service.HospedagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospedagem")
@Tag(name = "Gestão de quartos", description = "Serviço de gerenciamento da hospedagem e quartos.")
public class HospedagemController {

    private final HospedagemService service;

    @PostMapping
    @Operation(summary = "Cadastro de hospedagem")
    public ResponseEntity<HospedagemResponseDTO> cadastroHospedagem(@RequestBody @Valid HospedagemRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarHospedagem(request));
    }
    @PostMapping("/predio")
    @Operation(summary = "Cadastro de predio")
    public ResponseEntity<HospedagemResponseDTO> cadastroPredio(@RequestBody @Valid PredioRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarPredio(request));
    }
    @PostMapping("/quarto")
    @Operation(summary = "Cadastro de quarto")
    public ResponseEntity<HospedagemResponseDTO> cadastroQuarto(@RequestBody @Valid QuartoRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarQuarto(request));
    }
    @GetMapping
    @Operation(summary = "Buscar todas as hospedagens")
    public ResponseEntity<List<HospedagemResponseDTO>> getHospedagens(){
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarHospedagens());
    }
    @GetMapping("/{idHospedagem}")
    @Operation(summary = "Buscar hospedagem por id")
    public ResponseEntity<HospedagemResponseDTO> getHospedagemById(@PathVariable Long idHospedagem){
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarHospedagemPorId(idHospedagem));
    }
    @GetMapping("/predio/{idPredio}")
    @Operation(summary = "Buscar hospedagem por id do predio")
    public ResponseEntity<HospedagemResponseDTO> getHospedagemByIdPredio(@PathVariable Long idPredio){
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarHospedagemPorIdPredio(idPredio));
    }
    @GetMapping("/quarto/{idQuarto}")
    @Operation(summary = "Buscar hospedagem por id do quarto")
    public ResponseEntity<HospedagemResponseDTO> getHospedagemByIdQuarto(@PathVariable Long idQuarto){
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarHospedagemPorIdQuarto(idQuarto));
    }
    @PutMapping("/{idHospedagem}")
    @Operation(summary = "Altera hospedagem")
    public ResponseEntity<HospedagemResponseDTO> alteracaoHospedagem(@PathVariable Long idHospedagem,
                                                                     @RequestBody @Valid HospedagemRequestDTO request){
        return ResponseEntity.status(HttpStatus.OK).body(service.alterarHospedagem(idHospedagem,request));
    }
    @PutMapping("/predio/{idPredio}")
    @Operation(summary = "Altera predio")
    public ResponseEntity<HospedagemResponseDTO> alteracaoPredio(@PathVariable Long idPredio,
                                                                 @RequestBody @Valid PredioUpdateRequestDTO request){
        return ResponseEntity.status(HttpStatus.OK).body(service.alterarPredio(idPredio , request));
    }
    @PutMapping("/quarto/{idQuarto}")
    @Operation(summary = "Altera quarto")
    public ResponseEntity<HospedagemResponseDTO> alteracaoQuarto(@PathVariable Long idQuarto,
                                                                 @RequestBody @Valid QuartoUpdateRequestDTO request){
        return ResponseEntity.status(HttpStatus.OK).body(service.alterarQuarto(idQuarto, request));
    }
    @DeleteMapping("/{idHospedagem}")
    @Operation(summary = "Deletar hospedagem por id")
    public ResponseEntity<Void> alteracaoStatusHospedagem(@PathVariable Long idHospedagem){
        service.deleteHospedagem(idHospedagem);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/predio/{idPredio}")
    @Operation(summary = "Deletar predio por id")
    public ResponseEntity<Void> alteracaoStatusPredio(@PathVariable Long idPredio){
        service.deletePredio(idPredio);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/quarto/{idQuarto}")
    @Operation(summary = "Deletar quarto por id")
    public ResponseEntity<Void> alteracaoStatusQuarto(@PathVariable Long idQuarto){
        service.deleteQuarto(idQuarto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
