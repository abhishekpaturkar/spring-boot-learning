package com.abhishek.module2.advices;

import com.abhishek.module2.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

// Global Exception handler

@RestControllerAdvice // Will take all controller
public class GlobalExceptionHandler {


    // Defining exception handling for NoSuchElementException
    // Now it is global so we should call it as Resource instead of Employee
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException e) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    // Any other exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerException(Exception e) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInputValidationErrors(MethodArgumentNotValidException e) {
        List<String> errors = e
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("input validation failed")
                .subErrors(errors)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}