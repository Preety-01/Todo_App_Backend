package com.example.todo_application.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SuccessResponseHandler {

    public ResponseEntity<Object> generateResponse(String message, HttpStatus statusCode) {
        SuccessResponseInfo responseInfo = SuccessResponseInfo.builder()
                .successMessage(message)
                .httpStatusCode(statusCode.value())
                .build();
        return new ResponseEntity<>(responseInfo, statusCode);
    }
}
