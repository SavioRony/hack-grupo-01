package br.com.fiap.hackgrupo01.validations;

import br.com.fiap.hackgrupo01.exception.BadRequestException;
import br.com.fiap.hackgrupo01.model.dto.cliente.ClienteRequestDTO;

public class Validator {

    private Validator(){}

    static final String PAIS_ORIGEM = "Brasil";

    public static  void validateFields(ClienteRequestDTO cliente){

        StringBuilder sb = new StringBuilder();

        if(PAIS_ORIGEM.equalsIgnoreCase(cliente.getPaisOrigem()) && cliente.getCpf() == null){
            sb.append("Campo cpf Obrigatorio !!");
        }else if(!PAIS_ORIGEM.equalsIgnoreCase(cliente.getPaisOrigem()) && cliente.getPassaporte() == null){
            sb.append("Campo passaporte Obrigatorio");
        }

        if(!sb.isEmpty()){
            throw new BadRequestException(sb.toString());
        }
    }
}
