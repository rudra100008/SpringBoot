package com.blogrestapi.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogrestapi.DTO.JwtRequest;
import com.blogrestapi.DTO.JwtResponse;
import com.blogrestapi.DTO.UserDTO;
import com.blogrestapi.Security.JWTTokenHelper;
import com.blogrestapi.Security.UserDetailService;
import com.blogrestapi.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private JWTTokenHelper jwtTokenHelper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody JwtRequest request) {
        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails=this.userDetailService.loadUserByUsername(request.getUsername());
        String token= this.jwtTokenHelper.generateToken(userDetails);
        JwtResponse response=new JwtResponse();
        response.setToken(token);
        
        return ResponseEntity.ok(response);
    }
    public void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        //UsernamePassword holds the username and password of user before authentication after thr authentication
        //it will store the other data like role and permission
        this.authenticationManager.authenticate(token);// authenticationManger checks whether the username and password
        // matches with database username and password

    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, BindingResult result)
    {
        Map<String,Object> response=new HashMap<>();
        if (userDTO.getPassword().length()<3 &&userDTO.getPassword().length()>16) {
            result.rejectValue("password", "error.user","Password should be between 3 and 16 characters");
        }
        if (result.hasErrors()) {
            Map<String,Object> error=new HashMap<>();
            result.getFieldErrors().forEach(f->error.put(f.getField(), f.getDefaultMessage()));
            response.put("status", "BAD_REQUEST()");
            response.put("message", error);
            return ResponseEntity.badRequest().body(response);
        }
         UserDTO user=this.userService.registerNewUser(userDTO);
        return ResponseEntity.ok(user);
    }
}
