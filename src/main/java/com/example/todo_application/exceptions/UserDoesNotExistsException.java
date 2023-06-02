package com.example.todo_application.exceptions;

public class UserDoesNotExistsException extends Exception{
    public UserDoesNotExistsException(String message) {
        super(message);
    }
}
