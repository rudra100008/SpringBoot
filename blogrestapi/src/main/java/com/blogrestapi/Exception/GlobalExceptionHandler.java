package com.blogrestapi.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e)
    {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "INTERNAL_SERVER_ERROR(500)");
        response.put("message","Something went wrong: "+ e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handlerUserNotFound(UserNotFoundException ex)
    {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "NOT_FOUND(404)");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handleAlreadyExist(AlreadyExistsException al)
    {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "BAD_REQUEST(400)");
        response.put("message", al.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e)
    {
        Map<String,Object> response=new HashMap<>();
        response.put("status","NOT_FOUND(404)");
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "CONFLICT(409)");
        response.put("message", "Invalid user input");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
