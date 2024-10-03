package com.blogrestapi.Exception;

public class AlreadyExistsException  extends RuntimeException{
    public AlreadyExistsException(String message)
    {
        super(message);
    }
}
