package com.blogrestapi.Dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blogrestapi.Entity.User;
@Repository
public interface UserDao extends MongoRepository<User,Integer> {
    User findUserByUsername(Integer username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
