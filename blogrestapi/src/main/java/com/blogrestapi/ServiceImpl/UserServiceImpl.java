package com.blogrestapi.ServiceImpl;


import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogrestapi.Config.AppConstant;
import com.blogrestapi.DTO.UserDTO;
import com.blogrestapi.Dao.RoleDao;
import com.blogrestapi.Dao.UserDao;
import com.blogrestapi.Entity.Role;
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
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    private RoleDao roleDao;
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
        userDTO.setId((int) this.sequence.generateSequence("user_sequence"));//this generate news id
        if (this.userDao.existsByUsername(userDTO.getUsername())) {
            throw new AlreadyExistsException("username is already used");
        }
        if (this.userDao.existsByEmail(userDTO.getEmail())) {
            throw new AlreadyExistsException("email is already used");
        }
        if ( userDTO.getRole()==null ) {
            Role role=new Role(2,"ROLE_USER");
            userDTO.setRole(role);
        }
      
        userDTO.setPassword(encoder.encode(userDTO.getPassword())); 
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
        if ( user.getRole()==null ) {
            Role role=new Role(2,"ROLE_USER");
            user.setRole(role);
        }
       user.setUsername(userDTO.getUsername());
       user.setEmail(userDTO.getEmail());
      
       user.setPassword(encoder.encode(userDTO.getPassword())); 
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

    @Override
    public UserDTO registerNewUser(UserDTO userDTO) {
        User user=this.modelMapper.map(userDTO, User.class);
        if (this.userDao.existsByUsername(user.getUsername())) {
            throw new AlreadyExistsException("Username is already used");
        }
        if (this.userDao.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException("Email is already used");
        }
        user.setId((int)this.sequence.generateSequence("user_sequence"));
        user.setPassword(this.encoder.encode(user.getPassword()));
        user.setEnable(true);

        Role role=this.roleDao.findById(AppConstant.NORMAL_USER).orElseThrow(()->
            new ResourceNotFoundException("The role not found  with id: "+AppConstant.NORMAL_USER)
         );
         user.setRole(role);
         User saveUser=this.userDao.save(user);
       return modelMapper.map(saveUser, UserDTO.class);
    }
   
   

    
}
