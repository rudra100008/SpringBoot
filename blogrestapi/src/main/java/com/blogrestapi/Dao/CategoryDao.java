package com.blogrestapi.Dao;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.blogrestapi.Entity.Category;

public interface CategoryDao extends MongoRepository<Category,Integer> {
    
}
