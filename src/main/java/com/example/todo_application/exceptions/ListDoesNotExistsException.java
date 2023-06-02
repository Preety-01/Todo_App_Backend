package com.example.todo_application.exceptions;

public class ListDoesNotExistsException extends Exception{
    public ListDoesNotExistsException(String message) {
        super(message);
    }
}
