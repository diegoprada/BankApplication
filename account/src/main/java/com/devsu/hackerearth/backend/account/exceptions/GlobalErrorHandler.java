package com.devsu.hackerearth.backend.account.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.devsu.hackerearth.backend.account.model.ErrorDetails;
import com.devsu.hackerearth.backend.account.exceptions.BadRequestExceptions;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    @ExceptionHandler(BadRequestExceptions.class)
    public ResponseEntity<ErrorDetails> handleClientException(BadRequestExceptions ex, WebRequest request) {
                
        log.error("Exception caught in handleClientException :  {} {}", "OUT", ex.getMessage(),ex.getStackTrace());

        ErrorDetails errorDetails = new ErrorDetails(
            ex.getCodeHttp(),               // Código HTTP
            ex.getMessage(),                // Mensaje de error
            ex.getCode(),                   // Código personalizado de la excepción
            ex.getCategoryMessage(),        // Mensaje de categoría
            ex.getAction(),                 // Acción recomendada
            request.getDescription(false)   // Detalles adicionales de la petición
    );
        return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(ex.getCodeHttp()));

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        log.error("Exception caught in handleClientException :  {} {}", "OUT", ex.getMessage(), ex.getStackTrace());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
