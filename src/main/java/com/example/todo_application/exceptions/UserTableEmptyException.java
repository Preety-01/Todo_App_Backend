package com.example.todo_application.exceptions;

public class UserTableEmptyException extends Exception{
    public UserTableEmptyException(String message) {
        super(message);
    }
}
