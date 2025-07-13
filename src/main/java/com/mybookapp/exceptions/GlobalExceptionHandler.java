package com.mybookapp.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NegativeIdException.class)
    public ResponseEntity<Map<String, String>> handleNegativeIdException(
            NegativeIdException nie, WebRequest webRequest) {

        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", nie.getMessage());
        errorDetails.put("path", webRequest.getDescription(false));
        errorDetails.put("timestamp", LocalDateTime.now().toString());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEmployeeNotFoundException(
            BookNotFoundException enf, WebRequest webRequest) {

        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", enf.getMessage());
        errorDetails.put("path", webRequest.getDescription(false));
        errorDetails.put("timestamp", LocalDateTime.now().toString());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
