package com.example.exception;

class SIMDoesNotExistException extends Exception {
    public SIMDoesNotExistException(String message) {
        super(message);
    }
}
