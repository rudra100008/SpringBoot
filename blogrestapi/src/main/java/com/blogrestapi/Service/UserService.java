package com.blogrestapi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogrestapi.DTO.UserDTO;



@Service
public interface UserService {
    List<UserDTO> getUsers(); 
    UserDTO getUserById(String id);
    UserDTO createUser(UserDTO userDTO); 
    UserDTO updateUserById(String id, UserDTO userDTO);
    void deleteUserById(String id);
}
