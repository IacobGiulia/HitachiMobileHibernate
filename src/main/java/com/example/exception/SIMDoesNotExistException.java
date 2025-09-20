package com.example.exception;

class CustomerTableEmptyException extends Exception {
    public CustomerTableEmptyException(String message) {
        super(message);
    }
}
