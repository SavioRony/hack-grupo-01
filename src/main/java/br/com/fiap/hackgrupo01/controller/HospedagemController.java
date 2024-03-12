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
    public ResponseEntity<HospedagemResponse> cadastroHospedagem(@RequestBody @Valid HospedagemRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastroHospedagem(request));
    }
    @PostMapping("/predio")
    @Operation(summary = "Cadastro de predio")
    public ResponseEntity<HospedagemResponse> cadastroPredio(@RequestBody @Valid PredioRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastroPredio(request));
    }
    @PostMapping("/quarto")
    @Operation(summary = "Cadastro de quarto")
    public ResponseEntity<HospedagemResponse> cadastroQuarto(@RequestBody @Valid QuartoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastroQuarto(request));
    }
    @GetMapping
    @Operation(summary = "Buscar todas as hospedagens")
    public ResponseEntity<List<HospedagemResponse>> getHospedagens(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getHospedagens());
    }
    @GetMapping("/{idHospedagem}")
    @Operation(summary = "Buscar hospedagem por id")
    public ResponseEntity<HospedagemResponse> getHospedagemById(@PathVariable Long idHospedagem){
        return ResponseEntity.status(HttpStatus.OK).body(service.getHospedagemById(idHospedagem));
    }
    @GetMapping("/predio/{idPredio}")
    @Operation(summary = "Buscar hospedagem por id do predio")
    public ResponseEntity<HospedagemResponse> getHospedagemByIdPredio(@PathVariable Long idPredio){
        return ResponseEntity.status(HttpStatus.OK).body(service.getHospedagemByIdPredio(idPredio));
    }
    @GetMapping("/quarto/{idQuarto}")
    @Operation(summary = "Buscar hospedagem por id do quarto")
    public ResponseEntity<HospedagemResponse> getHospedagemByIdQuarto(@PathVariable Long idQuarto){
        return ResponseEntity.status(HttpStatus.OK).body(service.getHospedagemByIdQuarto(idQuarto));
    }
    @PutMapping("/{idHospedagem}")
    @Operation(summary = "Altera hospedagem")
    public ResponseEntity<HospedagemResponse> alteracaoHospedagem(@PathVariable Long idHospedagem,
                                                 @RequestBody @Valid HospedagemRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.alteracaoHospedagem(idHospedagem,request));
    }
    @PutMapping("/predio/{idPredio}")
    @Operation(summary = "Altera predio")
    public ResponseEntity<HospedagemResponse> alteracaoPredio(@PathVariable Long idPredio,
                                             @RequestBody @Valid PredioUpdateRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.alteracaoPredio(idPredio , request));
    }
    @PutMapping("/quarto/{idQuarto}")
    @Operation(summary = "Altera quarto")
    public ResponseEntity<HospedagemResponse> alteracaoQuarto(@PathVariable Long idQuarto,
                                             @RequestBody @Valid QuartoUpdateRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.alteracaoQuarto(idQuarto, request));
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
