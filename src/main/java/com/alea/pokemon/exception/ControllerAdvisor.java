package com.alea.pokemon.exception;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.UnknownHostException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid() {
        return buildErrorResponse(BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<Object> handleUnsupportedOperationException() {
        return buildErrorResponse(BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException() {
        return buildErrorResponse(BAD_REQUEST);
    }

    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<Object> handleUnknownHostException() {
        return buildErrorResponse(SERVICE_UNAVAILABLE);
    }

    @NotNull
    private ResponseEntity<Object> buildErrorResponse(@NotNull HttpStatus httpStatus) {
        return status(httpStatus).build();
    }

}
