package com.blogrestapi.Dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.blogrestapi.Entity.Category;

public interface CategoryDao extends JpaRepository<Category,Integer> {
    
}
