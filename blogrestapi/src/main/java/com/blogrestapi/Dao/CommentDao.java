package com.blogrestapi.Dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.blogrestapi.Entity.Comment;

public interface CommentDao extends MongoRepository<Comment,Integer> {
    
}
