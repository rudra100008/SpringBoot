package com.blogrestapi.Dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.blogrestapi.Entity.User;
public interface UserDao extends MongoRepository<User,String> {
    User findUserByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
