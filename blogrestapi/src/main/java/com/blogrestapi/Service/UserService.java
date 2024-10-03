<<<<<<< HEAD
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
=======
package com.blogrestapi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogrestapi.DTO.UserDTO;



@Service
public interface UserService {
    List<UserDTO> getUsers(); 
    UserDTO getUserById(int id);
    UserDTO createUser(UserDTO userDTO); 
    UserDTO updateUserById(int id, UserDTO userDTO);
    void deleteUserById(int id);
}
>>>>>>> 4e1d3c33e347acf859fd8818dbc58cef4c7a60f3
