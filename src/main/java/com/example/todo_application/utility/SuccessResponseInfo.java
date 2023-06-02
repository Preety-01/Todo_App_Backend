package com.example.todo_application.utility;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SuccessResponseInfo {

    private String successMessage;
    private int httpStatusCode;
}
