package com.LibraryManagementSystem.exception;

public class DuplicatePatronException extends RuntimeException{

    public DuplicatePatronException(String message) {
        super(message);
    }
}
