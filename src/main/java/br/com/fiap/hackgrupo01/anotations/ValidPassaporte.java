package br.com.fiap.hackgrupo01.anotations;


import br.com.fiap.hackgrupo01.anotations.implementation.PassaporteValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotNull
@NotBlank
@Pattern(regexp = "[a-zA-Z]+")
@Constraint(validatedBy = {PassaporteValidator.class})
@ReportAsSingleViolation
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassaporte {

    String message() default "Obrigatorio Passaporte !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
