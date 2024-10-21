package org.serratec.ecommerce.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ValidationExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
	    Map<String, String> response = new HashMap<>();
	    
	    if (ex.getCause() != null && ex.getCause().getCause() != null) {
	        String message = ex.getCause().getCause().getMessage();
	        
	        if (message.contains("cliente_email_key")) {
	            response.put("email", "O email informado já está em uso.");
	        } else if (message.contains("cliente_cpf_key")) {
	            response.put("cpf", "O CPF informado já está em uso.");
	        } else {
	            response.put("erro", "Violação de integridade de dados.");
	        }
	     } else {
	        response.put("erro", "Erro de integridade de dados desconhecido.");
	    }

	    return ResponseEntity.badRequest().body(response);
	}
}
