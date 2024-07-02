package com.example.todolist.exception;

public class BaseBusinessLogicException extends RuntimeException{
    public BaseBusinessLogicException(String message) {
        super(message);
    }
}
