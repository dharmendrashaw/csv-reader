package com.test.exception;

public class InValidOrEmptyDataException extends RuntimeException {

    public InValidOrEmptyDataException(String msg) {
        super(msg);
    }
}
