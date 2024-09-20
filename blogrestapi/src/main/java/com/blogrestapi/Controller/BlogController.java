package com.blogrestapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@CrossOrigin("http://localhost:5173")
public class BlogController {
    @Autowired
    private UserService userService;

    //this handler get all the user data from the database
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUser()
    {
        List<UserDTO> getAllUser=this.userService.getUsers();
        if (getAllUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(getAllUser);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id")int id)
    {
        try {
            UserDTO getUserById=this.userService.getUserById(id);
            return ResponseEntity.ok(getUserById);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
    }
    //this handler  get the input from the user and post the data to database
    @PostMapping("/users")
    public ResponseEntity<?> postUser(@Valid @RequestBody UserDTO user,BindingResult result) {
        user.setEnable(true);
        try{
            if (result.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
            }
            UserDTO saveUser=this.userService.createUser(user);
            return  ResponseEntity.status(HttpStatus.CREATED).body(saveUser);

        }catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
        
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") int id )
    {
        try{
             this.userService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted");
        }catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
        }
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<?> putUserById(@PathVariable("id") int id, @RequestBody UserDTO  user) {
        try {
            UserDTO updatedUser=this.userService.updateUserById(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }
    

   
   
   
}
