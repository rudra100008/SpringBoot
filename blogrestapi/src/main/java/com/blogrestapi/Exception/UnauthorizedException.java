package com.blogrestapi.Exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message)
    {
        super(message);
    }
}
