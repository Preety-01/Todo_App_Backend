package com.example.todo_application.exceptions;

public class ItemAlreadyExistsException extends Exception{
    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}
