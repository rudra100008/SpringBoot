package com.validationproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.validationproject.entity.User;

import jakarta.validation.Valid;


@Controller
public class MainController {

    //http://localhost:8080/form
    @GetMapping("/form")
    public String formHandler(Model model){

        System.out.println("Form Handler running.........");
        model.addAttribute("User", new User());
        return "form";
    }
    //handler for processing login data
    //http://localhost:8080/login
    @PostMapping("/login")
    public String loginHandler(){
        System.out.println("Login Handler...........");
        return "success";
    }
    //handler for processing singup data
    @PostMapping("/signup")
    public String singupHandler( @Valid @ModelAttribute("User") User user,BindingResult result,Model model){
        if(result.hasErrors()){
            return "form";
        }
        System.out.println(user);
        System.out.println("Sign up Handler........");
        return "redirect:/form";
    }
}
