package com.example.todo_application.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ErrorInfo {
    private LocalDate timestamp;
    private int statusCode;
    private String errorMessage;
}
