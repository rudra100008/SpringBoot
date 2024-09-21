package com.blogrestapi.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handlerUserNotFound(UserNotFoundException ex)
    {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handleAlreadyExist(AlreadyExistsException al)
    {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", al.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
