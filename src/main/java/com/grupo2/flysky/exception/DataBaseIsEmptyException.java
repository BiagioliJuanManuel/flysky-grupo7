package com.grupo2.flysky.exception;

public class DataBaseIsEmptyException extends RuntimeException{
    public DataBaseIsEmptyException(String message) {
        super(message);
    }
}
