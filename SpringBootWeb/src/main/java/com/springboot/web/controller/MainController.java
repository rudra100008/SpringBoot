package com.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.web.Modul.User;

import org.springframework.ui.Model;
@Controller
public class MainController {

    @GetMapping("/userForm")
    public String showForm(Model model) {
    	model.addAttribute("user",new User());
        return "userForm";
    }
    @PostMapping("/userForm")
    public String  submitForm(@ModelAttribute("user")  User user,Model model) {
      model.addAttribute("user", user);
        return "userResult";
    }
}
