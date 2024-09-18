package com.blogrestapi.Service;

import java.util.List;

import com.blogrestapi.Dao.UserDao;
import com.blogrestapi.Entity.User;

public class UserServiceImpl implements UserService {
    
    private UserDao userDao;
    @Override
    public List<User> getUser() {
    
        return this.userDao.findAll();
    }
    @Override
    public User getUserById(int id) {
       
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }
    @Override
    public User postUser(User user) {
      
        return this.userDao.save(user);
    }
    @Override
    public User updateUserById(int id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'updateUserById'");
    }
    @Override
    public User deleteUserById(int id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'deleteUserById'");
    }

    
}
