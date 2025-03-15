package com.osipov.infprot.lab1.exceptions.model;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
