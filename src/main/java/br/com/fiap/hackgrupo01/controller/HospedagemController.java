package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.*;
import br.com.fiap.hackgrupo01.service.HospedagemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospedagem")
public class HospedagemController {

    private final HospedagemService service;

    @PostMapping
    public ResponseEntity<?> cadastroHospedagem(@RequestBody @Valid HospedagemRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastroHospedagem(request));
    }
    @PostMapping("/predio")
    public ResponseEntity<?> cadastroPredio(@RequestBody @Valid PredioRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastroPredio(request));
    }
    @PostMapping("/quarto")
    public ResponseEntity<?> cadastroQuarto(@RequestBody @Valid QuartoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastroQuarto(request));
    }
    @GetMapping
    public ResponseEntity<?> getHospedagens(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getHospedagens());
    }
    @GetMapping("/{idHospedagem}")
    public ResponseEntity<?> getHospedagemById(@PathVariable Long idHospedagem){
        return ResponseEntity.status(HttpStatus.OK).body(service.getHospedagemById(idHospedagem));
    }
    @GetMapping("/predio/{idPredio}")
    public ResponseEntity<?> getHospedagemByIdPredio(@PathVariable Long idPredio){
        return ResponseEntity.status(HttpStatus.OK).body(service.getHospedagemByIdPredio(idPredio));
    }
    @GetMapping("/quarto/{idQuarto}")
    public ResponseEntity<?> getHospedagemByIdQuarto(@PathVariable Long idQuarto){
        return ResponseEntity.status(HttpStatus.OK).body(service.getHospedagemByIdQuarto(idQuarto));
    }
    @PutMapping("/{idHospedagem}")
    public ResponseEntity<?> alteracaoHospedagem(@PathVariable Long idHospedagem,
                                                 @RequestBody @Valid HospedagemRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.alteracaoHospedagem(idHospedagem,request));
    }
    @PutMapping("/predio/{idPredio}")
    public ResponseEntity<?> alteracaoPredio(@PathVariable Long idPredio,
                                             @RequestBody @Valid PredioUpdateRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.alteracaoPredio(idPredio , request));
    }
    @PutMapping("/quarto/{idQuarto}")
    public ResponseEntity<?> alteracaoQuarto(@PathVariable Long idQuarto,
                                             @RequestBody @Valid QuartoUpdateRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.alteracaoQuarto(idQuarto, request));
    }
}
