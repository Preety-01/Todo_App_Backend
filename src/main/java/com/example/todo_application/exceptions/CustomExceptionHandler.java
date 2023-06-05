package com.example.todo_application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {
        ErrorInfo errorInfo = ErrorInfo.builder()
                .timestamp(LocalDate.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(exception.getMessage())
                .build();
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UserAlreadyExistsException.class,
            ListAlreadyExistsException.class,
            ItemAlreadyExistsException.class})
    public ResponseEntity<ErrorInfo> alreadyExistsExceptionHandler(Exception exception) {
        ErrorInfo errorInfo = ErrorInfo.builder()
                .timestamp(LocalDate.now())
                .statusCode(HttpStatus.CONFLICT.value())
                .errorMessage(exception.getMessage())
                .build();
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UserDoesNotExistsException.class,
            ListDoesNotExistsException.class,
            UserTableEmptyException.class,
            ListsNotFoundForUserException.class,
            ItemNotFoundForListException.class})
    public ResponseEntity<ErrorInfo> doesNotExistsExceptionHandler(Exception exception) {
        ErrorInfo errorInfo = ErrorInfo.builder()
                .timestamp(LocalDate.now())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorMessage(exception.getMessage())
                .build();
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
    }
}
