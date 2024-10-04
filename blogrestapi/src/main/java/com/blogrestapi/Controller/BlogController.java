
package com.blogrestapi.Controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.blogrestapi.DTO.UserDTO;
import com.blogrestapi.Service.UserService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class BlogController {
    @Autowired
    private UserService userService;

    // this handler get all the user data from the database
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> getAllUser = this.userService.getUsers();
        if (getAllUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(getAllUser);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        UserDTO getUserById = this.userService.getUserById(id);
        return ResponseEntity.ok(getUserById);
    }

    // this handler get the input from the user and post the data to database
    @PostMapping("/users")
    public ResponseEntity<?> postUser(@Valid @RequestBody UserDTO user, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        user.setEnable(true);
        String rawPassword=user.getPassword();
        if (rawPassword.length()<3 && rawPassword.length()>16) {
            result.rejectValue("password", "error.user","Password should be less than 3 and greater than 16");
        }
        if (result.hasErrors()) {
            Map<String,Object> errorMessage=new HashMap<>();
            result.getFieldErrors().forEach(error->{
                errorMessage.put(error.getField(),error.getDefaultMessage());
            });
            response.put("status","BAD_REQUEST(400)");
            response.put("message",errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        UserDTO saveUser = this.userService.createUser(user);
        response.put("message", "User inserted successfully");
        response.put("status", "CREATED(201)");
        response.put("data", saveUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        UserDTO getUserById = this.userService.getUserById(id);
        this.userService.deleteUserById(id);
        response.put("message", "User deleted successfully");
        response.put("status", "OK(200)");
        response.put("data", getUserById);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> putUserById(@PathVariable("id") int id,@Valid @RequestBody UserDTO user,BindingResult result) {
        Map<String,Object> response=new HashMap<>();
        if (result.hasErrors()) {
            Map<String,Object> fieldError=new HashMap<>();
            result.getFieldErrors().forEach(err->fieldError.put(err.getField(), err.getDefaultMessage()));
            response.put("status", "BAD_REQUEST(400)");
            response.put("message", fieldError);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        UserDTO updatedUser = this.userService.updateUserById(id, user);
        return ResponseEntity.ok(updatedUser);
    }

}
