package com.blogrestapi.Dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.blogrestapi.Entity.Role;

public interface RoleDao extends MongoRepository<Role,Integer> {
    
}
