package com.example.todo_application.exceptions;

public class ListAlreadyExistsException extends Exception{
    public ListAlreadyExistsException(String message) {
        super(message);
    }
}
