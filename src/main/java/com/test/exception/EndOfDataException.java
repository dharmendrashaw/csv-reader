package com.test.exception;

public class EndOfDataException extends RuntimeException {

    public EndOfDataException(String message) {
        super(message);
    }
}
