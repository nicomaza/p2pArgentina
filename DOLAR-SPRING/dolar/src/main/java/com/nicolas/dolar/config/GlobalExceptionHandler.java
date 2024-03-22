package com.nicolas.dolar.config;

import com.nicolas.dolar.dtos.common.ErrorApi;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorApi errorApi = new ErrorApi(
                LocalDateTime.now().toString(), // Asegúrate de que el timestamp tenga el formato correcto
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getBindingResult().getFieldError().getDefaultMessage() // Mensaje de error específico
        );
        return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
    }

    // Aquí puedes agregar manejadores para otros tipos de excepciones si es necesario

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorApi> handleUserNotValidException(DataIntegrityViolationException ex) {
        ErrorApi errorApi = new ErrorApi(
                LocalDateTime.now().toString(), // Asegúrate de que el timestamp tenga el formato correcto
                HttpStatus.BAD_REQUEST.value(),
                "User Not Valid",
                ex.getMessage() // Mensaje de error específico para UserNotValidException
        );
        return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
    }
}

