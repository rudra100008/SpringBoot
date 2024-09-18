package com.blogrestapi.Service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.blogrestapi.Entity.User;

@Service
public interface  UserService {  
    public List<User> getUser(); 
    public User getUserById(int id);
    public User postUser(User user); 
    public User updateUserById(int id);
    public User deleteUserById(int id);
}
