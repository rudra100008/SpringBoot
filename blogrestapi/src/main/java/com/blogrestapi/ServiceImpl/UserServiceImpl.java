package com.blogrestapi.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogrestapi.DTO.UserDTO;
import com.blogrestapi.Dao.UserDao;
import com.blogrestapi.Entity.User;
import com.blogrestapi.Exception.AlreadyExistsException;
import com.blogrestapi.Exception.ResourceNotFoundException;
import com.blogrestapi.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SequenceGeneratorService sequence;
    @Override
    public List<UserDTO> getUsers() {
        return this.userDao.findAll().stream()
         .map(user->modelMapper.map(user, UserDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(int id) {
        return this.userDao.findById(id).map(user->modelMapper.map(user,UserDTO.class))
        .orElseThrow(()->new ResourceNotFoundException("User not found with id: "+id));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setId((int) this.sequence.generateSequence("user_sequence"));
        if (this.userDao.existsByUsername(userDTO.getUsername())) {
            throw new AlreadyExistsException("username is already used");
        }
        if (this.userDao.existsByEmail(userDTO.getEmail())) {
            throw new AlreadyExistsException("email is already used");
        }
        User user=modelMapper.map(userDTO, User.class);
        User savedUser=this.userDao.save(user);
        return modelMapper.map(savedUser,UserDTO.class);
    }

    @Override
    public UserDTO updateUserById(int id, UserDTO userDTO) {
        User user=this.userDao.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id: "+id)); 
        if (!user.getUsername().equals(userDTO.getUsername()) && this.userDao.existsByUsername(userDTO.getUsername())  ) {
            throw new AlreadyExistsException("Username is already used");
        }
        if ( !user.getEmail().equals(userDTO.getEmail()) &&  this.userDao.existsByEmail(userDTO.getEmail())) {
            throw new AlreadyExistsException("Email is already used");
        }
       user.setUsername(userDTO.getUsername());
       user.setEmail(userDTO.getEmail());
       user.setPassword(userDTO.getPassword());
       user.setEnable(true);
      User updateduser= this.userDao.save(user);
      return modelMapper.map(updateduser, UserDTO.class);

    }

    @Override
    public void deleteUserById(int id) {
        if (!this.userDao.existsById(id)) {
            throw new ResourceNotFoundException("User not found by id: "+id);
        }
       this.userDao.deleteById(id);
    }
   
   

    
}
