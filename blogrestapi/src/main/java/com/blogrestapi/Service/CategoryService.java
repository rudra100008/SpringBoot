package com.blogrestapi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogrestapi.DTO.CategoryDTO;

@Service
public interface CategoryService {
    //create category
    CategoryDTO createCategory(CategoryDTO category);

    //getAll Category
    List<CategoryDTO> getAllCategory();

    //get Category by id
    CategoryDTO getCategoryBYId(String id);

    //delete category
    void deleteCategory(String id);

    //update Category
    CategoryDTO updateCategory(String id,CategoryDTO category);
}
