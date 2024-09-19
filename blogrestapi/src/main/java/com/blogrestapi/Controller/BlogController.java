package com.blogrestapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.blogrestapi.Service.UserService;


@RestController
public class BlogController {
    @Autowired
    private UserService userService;

   
   
   
}
