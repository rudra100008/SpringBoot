package com.company.springbootpractice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping("/home")
    @ResponseBody
    public String handle(){
        
        return "this is first springboot in vscode";
    }
}
