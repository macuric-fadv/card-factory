package com.marsus.demo.cardfactory.exception;

import com.marsus.demo.cardfactory.rest.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Application exception handler, maps incoming exception - thrown by application and returns appropriate
 * error response.
 */
@ControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleException(IllegalArgumentException ex, WebRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .description(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleException(NotFoundException ex, WebRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .description(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleExceptionGeneral(Exception ex, WebRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .description(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
