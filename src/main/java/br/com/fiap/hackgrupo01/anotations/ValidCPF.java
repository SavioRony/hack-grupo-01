package br.com.fiap.hackgrupo01.anotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotNull
@NotBlank
@Pattern(regexp = "[a-zA-Z]+")
@Constraint(validatedBy = {CPFValidator.class})
@ReportAsSingleViolation
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCPF {

    String message() default "Obrigatorio CPF !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
