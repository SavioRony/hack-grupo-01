package br.com.fiap.hackgrupo01.anotations.implementation;

import br.com.fiap.hackgrupo01.anotations.ValidCPF;
import br.com.fiap.hackgrupo01.model.cliente.Cliente;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<ValidCPF, String> {
    @Override
    public void initialize(ValidCPF constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Cliente myClass = (Cliente) context;
        return "BRASIL".equals(myClass.getPaisOrigem()) || (value != null && !value.isEmpty());
    }
}
