package com.epam.exceptions;

public class PropertiesNotLoadedException extends RuntimeException {
    public PropertiesNotLoadedException(String message, Throwable cause) {
        super(message, cause);
    }
}
