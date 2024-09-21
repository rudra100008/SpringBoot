package com.blogrestapi.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.blogrestapi.DTO.UserDTO;
import com.blogrestapi.Exception.AlreadyExistsException;
import com.blogrestapi.Service.UserService;

import jakarta.persistence.EntityNotFoundException;
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
        try {
            UserDTO getUserById = this.userService.getUserById(id);
            return ResponseEntity.ok(getUserById);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong: ");
        }
    }

    // this handler get the input from the user and post the data to database
    @PostMapping("/users")
    public ResponseEntity<?> postUser(@Valid @RequestBody UserDTO user, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        user.setEnable(true);
        try {
            if (result.hasErrors()) {
                Map<String, String> errorMap = new HashMap<>();
                result.getFieldErrors().forEach(error -> {
                    errorMap.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
            }
            
            UserDTO saveUser = this.userService.createUser(user);
            response.put("message", "User inserted successfully");
            response.put("status", "success");
            response.put("data", saveUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (AlreadyExistsException ex) {
            response.put("status", "error");
            response.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (DataIntegrityViolationException di) {
            response.put("status", "error");
            response.put("message", "Invalid user input");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
    

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserDTO getUserById = this.userService.getUserById(id);
            this.userService.deleteUserById(id);
            response.put("message", "User deleted successfully");
            response.put("status", "success");
            response.put("data", getUserById);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (EntityNotFoundException entity) {
            response.put("status", "error");
            response.put("message", "User not found to delete");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> putUserById(@PathVariable("id") int id, @RequestBody UserDTO user) {
        try {
            UserDTO updatedUser = this.userService.updateUserById(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong: ");
        }
    }

}
