package com.blogrestapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blogrestapi.Entity.User;
import com.blogrestapi.Service.UserService;

@ResponseBody
public class BlogController {
    @Autowired
    private UserService userService;
    
    public ResponseEntity<User> saveUser(@RequestBody User user)
    {
        User savedUser=this.userService.postUser(user);
        return ResponseEntity.ok(savedUser);
    }
}
