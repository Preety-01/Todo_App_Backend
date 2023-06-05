package com.example.todo_application.exceptions;

public class ItemNotFoundForListException extends Exception{
    public ItemNotFoundForListException(String message) {
        super(message);
    }
}
