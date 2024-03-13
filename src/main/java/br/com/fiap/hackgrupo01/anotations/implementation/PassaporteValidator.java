package br.com.fiap.hackgrupo01.anotations.implementation;

import br.com.fiap.hackgrupo01.anotations.ValidPassaporte;
import br.com.fiap.hackgrupo01.model.cliente.Cliente;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PassaporteValidator implements ConstraintValidator<ValidPassaporte, String> {

    @Override
    public void initialize(ValidPassaporte constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Cliente myClass = (Cliente) context;
        return "BRASIL".equals(myClass.getPaisOrigem()) || (value != null && !value.isEmpty()); // Origem é obrigatória quando o país não é BRASIL
    }
}
