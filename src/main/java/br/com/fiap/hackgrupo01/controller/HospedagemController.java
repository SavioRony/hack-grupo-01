package br.com.fiap.hackgrupo01.controller;

import br.com.fiap.hackgrupo01.model.dto.hospedagem.HospedagemRequest;
import br.com.fiap.hackgrupo01.model.dto.hospedagem.PredioRequest;
import br.com.fiap.hackgrupo01.service.HospedagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospedagem")
public class HospedagemController {

    @Autowired
    private HospedagemService service;

    @PostMapping
    public ResponseEntity<?> cadastroHospedagem(@RequestBody @Valid HospedagemRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastroHospedagem(request));
    }

    @PostMapping("/predio")
    public ResponseEntity<?> cadastroPredio(@RequestBody PredioRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastroPredio(request));
    }
}
