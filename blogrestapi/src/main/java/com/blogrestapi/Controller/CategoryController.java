package com.blogrestapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blogrestapi.DTO.CategoryDTO;
import com.blogrestapi.Service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    //create category handler
    @PostMapping("/category")
    public ResponseEntity<?> postCategory(@RequestBody CategoryDTO categoryDTO)
    {
       CategoryDTO savedCategory= this.categoryService.createCategory(categoryDTO);
       return ResponseEntity.status(HttpStatus.OK).body(savedCategory);
    }
    //Get all category
    @GetMapping("/category")
    public ResponseEntity<?> getCategory()
    {
        List<CategoryDTO> getAllCaetgory=this.categoryService.getAllCategory();
        return ResponseEntity.ok(getAllCaetgory);
    }
    //Get Category from the ID
    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") int id)
    {
        CategoryDTO categoryDTO=this.categoryService.getCategoryBYId(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }
    //update category by id
    @PutMapping("/category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") int id,@RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO updateCategory=this.categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateCategory);
    }
    //delete category by id
    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") int id)
    {
        this.categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
