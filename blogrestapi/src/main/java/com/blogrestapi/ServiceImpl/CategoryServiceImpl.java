package com.blogrestapi.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogrestapi.DTO.CategoryDTO;
import com.blogrestapi.Dao.CategoryDao;
import com.blogrestapi.Entity.Category;
import com.blogrestapi.Exception.ResourceNotFoundException;
import com.blogrestapi.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
       Category category=modelMapper.map(categoryDTO,Category.class);
       Category savedCategory=this.categoryDao.save(category);
       return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
         return this.categoryDao.findAll().stream()
        .map(category->modelMapper.map(category,CategoryDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryBYId(int id) {
       return this.categoryDao.findById(id).map(category->modelMapper.map(category, CategoryDTO.class))
       .orElseThrow(()->new ResourceNotFoundException("Category with this "+id+ " not found "));
    }

    @Override
    public void deleteCategory(int id) {
       this.categoryDao.deleteById(id);
    }

    @Override
    public CategoryDTO updateCategory(int id, CategoryDTO categoryDTO) {
        Category category=this.categoryDao.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("Category  with this "+id+ " not found"));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
       Category updatedCategory= this.categoryDao.save(category);
       return modelMapper.map(updatedCategory,CategoryDTO.class);
    }
    
}
