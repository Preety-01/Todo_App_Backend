package com.example.todo_application.exceptions;

public class ListsNotFoundForUserException extends Exception{
    public ListsNotFoundForUserException(String message) {
        super(message);
    }
}
