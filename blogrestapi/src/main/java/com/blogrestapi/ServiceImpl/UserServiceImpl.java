package com.blogrestapi.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogrestapi.DTO.UserDTO;
import com.blogrestapi.Dao.UserDao;
import com.blogrestapi.Entity.User;
import com.blogrestapi.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<UserDTO> getUsers() {
        return this.userDao.findAll().stream()
         .map(user->modelMapper.map(user, UserDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(int id) {
        return this.userDao.findById(id).map(user->modelMapper.map(user,UserDTO.class)).orElse(null);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user=modelMapper.map(userDTO, User.class);
        User savedUser=this.userDao.save(user);
        return modelMapper.map(savedUser,UserDTO.class);
    }

    @Override
    public UserDTO updateUserById(int id, UserDTO userDTO) {
       User user=this.userDao.findById(id).orElseThrow(()->new RuntimeException("User not found")); 
       modelMapper.map(userDTO, user);
      User updateduser= this.userDao.save(user);
      return modelMapper.map(updateduser, UserDTO.class);

    }

    @Override
    public void deleteUserById(int id) {
       this.userDao.deleteById(id);
    }
   
   

    
}
