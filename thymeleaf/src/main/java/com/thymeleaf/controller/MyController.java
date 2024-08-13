package com.thymeleaf.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MyController {
    //http://localhost:8080/about
    @RequestMapping(value = "/about",method = RequestMethod.GET)
    public String abouthandler(Model model){
        LocalDateTime localDateTime=LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd.MM.yyyy  HH:mm:ss");
        System.out.println("About handler running.......");
        model.addAttribute("name","Ashum");
        model.addAttribute("date", new Date().toString());
        model.addAttribute("currentDateTime",dateTimeFormatter.format(localDateTime));
        return "about";
    }

    //http://localhost:8080/list
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String listHandler(Model model){
        System.out.println("list handler........");
        List<String> nameList=new ArrayList<>();
        nameList.add("Ram");
        nameList.add("Shyam");
        nameList.add("Hari");
        nameList.add("Shiva");
        nameList.add("Rudra");
        model.addAttribute("nameList", nameList);
        
        return "list";
    }

    //http://localhost:8080/user
    @GetMapping(value = "/user")
    public String getMethodName(Model model) {
        model.addAttribute("message","");
        return "user";
    }
    

    //http://localhost:8080/user
    @RequestMapping(value = "/user", method=RequestMethod.POST)
    public String userHandler(Model model,@RequestParam(value = "password", required=false) String password) {
       if("user".equals(password)){
        model.addAttribute("message", "This is User.");
       }else if ("admin".equals(password)) {
        model.addAttribute("message", " This is Admin.");
       }else{
        model.addAttribute("message", "Invalid Password.");
       }
        return "user";
    }
    
}
