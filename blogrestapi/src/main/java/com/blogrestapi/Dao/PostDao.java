package com.blogrestapi.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogrestapi.Entity.Post;

@Repository
public interface PostDao extends JpaRepository<Post,Integer> {
    
}
