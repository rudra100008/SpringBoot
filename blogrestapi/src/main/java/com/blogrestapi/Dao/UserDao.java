package com.blogrestapi.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogrestapi.Entity.User;
@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    
}
