package com.switchfully.www.exceptions;

public class UnauthorizatedException extends RuntimeException {
    public UnauthorizatedException(String message) {
        super(message);
    }
}
