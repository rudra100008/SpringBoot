package com.blogrestapi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogrestapi.DTO.UserDTO;



@Service
public interface UserService {
    List<UserDTO> getUsers(); 
    UserDTO getUserById(int id);
    UserDTO registerNewUser(UserDTO userDTO);
    UserDTO createUser(UserDTO userDTO); 
    UserDTO updateUserById(int id, UserDTO userDTO);
    void deleteUserById(int id);
}
