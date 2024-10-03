<<<<<<< HEAD
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
    CategoryDTO getCategoryBYId(int id);

    //delete category
    void deleteCategory(int id);

    //update Category
    CategoryDTO updateCategory(int id,CategoryDTO category);
}
=======
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
    CategoryDTO getCategoryBYId(int id);

    //delete category
    void deleteCategory(int id);

    //update Category
    CategoryDTO updateCategory(int id,CategoryDTO category);
}
>>>>>>> 4e1d3c33e347acf859fd8818dbc58cef4c7a60f3
