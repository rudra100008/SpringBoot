<<<<<<< HEAD
package com.blogrestapi.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogrestapi.Dao.UserDao;
import com.blogrestapi.Entity.User;
import com.blogrestapi.Exception.ResourceNotFoundException;
@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user form database 
        User user= this.userDao.findByUsername(username)
        .orElseThrow(()->new ResourceNotFoundException("User("+username+") not found"));

        return user;
    }
    
}
=======
package com.blogrestapi.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogrestapi.Dao.UserDao;
import com.blogrestapi.Entity.User;
import com.blogrestapi.Exception.ResourceNotFoundException;
@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user form database 
        User user= this.userDao.findByUsername(username)
        .orElseThrow(()->new ResourceNotFoundException("User("+username+") not found"));

        return user;
    }
    
}
>>>>>>> 4e1d3c33e347acf859fd8818dbc58cef4c7a60f3
