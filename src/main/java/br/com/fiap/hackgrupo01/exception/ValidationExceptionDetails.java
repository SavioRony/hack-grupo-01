package br.com.fiap.hackgrupo01.exception;

import br.com.fiap.hackgrupo01.model.dto.error.ExceptionDetails;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class ValidationExceptionDetails extends ExceptionDetails {
    private final String fields;
    private final String fieldsMessage;
}
