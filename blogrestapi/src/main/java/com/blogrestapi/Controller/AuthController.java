package com.blogrestapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogrestapi.DTO.JwtRequest;
import com.blogrestapi.DTO.JwtResponse;
import com.blogrestapi.Security.JWTTokenHelper;
import com.blogrestapi.Security.UserDetailService;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private JWTTokenHelper jwtTokenHelper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailService userDetailService;

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
}
