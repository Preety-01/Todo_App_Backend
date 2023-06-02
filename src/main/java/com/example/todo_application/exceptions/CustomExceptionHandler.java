package com.example.todo_application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception){
        ErrorInfo errorInfo = ErrorInfo.builder()
                .timestamp(LocalDate.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(exception.getMessage())
                .build();
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorInfo> userAlreadyExistsExceptionHandler(
            UserAlreadyExistsException userAlreadyExistsException){
        ErrorInfo errorInfo = ErrorInfo.builder()
                .timestamp(LocalDate.now())
                .statusCode(HttpStatus.CONFLICT.value())
                .errorMessage(userAlreadyExistsException.getMessage())
                .build();
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.CONFLICT);
    }
}
