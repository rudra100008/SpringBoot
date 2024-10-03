package com.practicespringboot.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainCotroller {

    @GetMapping("/home")
    public ResponseEntity<String> handler()
    {
        HttpHeaders header =new HttpHeaders();
        header.add("Customer-Header", "value");
        return new ResponseEntity<>("Hello!! I am Spring Boot",header,HttpStatus.OK);
    }
    
}
