package com.company.controller;

import com.company.entity.User;
import com.company.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "home"; // Corresponds to home.jsp
    }

    @PostMapping("/login")
    public String registerUser(@ModelAttribute User user, Model model) {
        this.userService.saveUser(user); 
        return "redirect:/users"; // Redirect to users list after registration
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> users = userService.readUser(); // Fetch list of users
        model.addAttribute("data", users); // Add the list to the model
        return "success";  // Corresponds to success.jsp
    }

    @GetMapping("/delete/{id}")
    public RedirectView handleDelete(@PathVariable("id") int id,HttpServletRequest request) {
        this.userService.deleteUser(id); 
        RedirectView redirectView =new RedirectView();
        redirectView.setUrl(request.getContextPath()+"/users");
        return redirectView; // Redirect to users list after deletion
    }
}
