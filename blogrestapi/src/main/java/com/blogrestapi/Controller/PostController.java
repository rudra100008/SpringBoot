package com.blogrestapi.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogrestapi.DTO.PostDTO;
import com.blogrestapi.Service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    //getting the all post  in the database
    @GetMapping("/posts")
    public ResponseEntity<?> getAllPost(
        @RequestParam(value = "pageNumber",defaultValue = "0",required = false)int pageNumber,
        @RequestParam(value = "pageSize",defaultValue = "2",required = false)int pageSize) 
    {
        List<PostDTO> post = this.postService.getAllPost(pageNumber,pageSize);
        return ResponseEntity.ok(post);
    }


    //handler for getting single by id of the particular user
    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") String id) {
        PostDTO postDTO = this.postService.getPostById(id);
        return ResponseEntity.ok(postDTO);
    }

    //handler for the creating or saving the post in the database
    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDTO postDTO,BindingResult result,
            @RequestParam("userId") String userId,
            @RequestParam("categoryId") String categoryId) 
    {
        Map<String,Object> response=new HashMap<>();
        if (result.hasErrors()) {
            Map<String,Object> error=new HashMap<>();
            result.getFieldErrors().forEach(err->error.put(err.getField(), err.getDefaultMessage()));
            response.put("status", "BAD_REQUEST(400)");
            response.put("message", error);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        PostDTO savedPost=this.postService.createPost(postDTO, userId, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    //handler for updating the post
    @PutMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") String id,
    @Valid @RequestBody PostDTO postDTO,
    BindingResult result,
    @RequestParam("userId")String userId,
    @RequestParam("categoryId")String categoryId)
    {
        Map<String,Object> response=new HashMap<>();
        if (result.hasErrors()) {
            Map<String,Object> error=new HashMap<>();
            result.getFieldErrors().forEach(field->error.put(field.getField(), field.getDefaultMessage()));
            response.put("status", "BAD_REQUEST(400)");
            response.put("message", error);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        PostDTO updatePost=this.postService.updatePostById(id, postDTO, userId, categoryId);
        return ResponseEntity.ok(updatePost);
    }
    //handler for deleting the posts
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id")String id)
    {
        Map<String,Object> response=new HashMap<>();
        PostDTO getPost=this.postService.getPostById(id);
        this.postService.deletePostById(id);
        response.put("status", "Ok(200)");
        response.put("message", getPost);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
    //handler for patching the post
    @PatchMapping("/posts/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
    @Valid @RequestBody PostDTO postDTO,
    BindingResult result,
    @RequestParam("userId")String userId,
    @RequestParam("categoryId")String categoryId)
    {
        Map<String,Object> response=new HashMap<>();
        if (result.hasErrors()) {
            Map<String,Object> error=new HashMap<>();
            result.getFieldErrors().forEach(err->error.put(err.getField(), err.getDefaultMessage()));
            response.put("status", "BAD_REQUEST(400)");
            response.put("message", error);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        PostDTO updatePost=this.postService.updatePostField(id, postDTO, userId, categoryId);
        return ResponseEntity.ok(updatePost);
    }

    //get post of a particular user by using id
    @GetMapping("posts/user/{userId}")
    public ResponseEntity<?> getPostByUser(@PathVariable("userId")String userId)
    {
        Map<String,Object> response=new HashMap<>();
        List<PostDTO> post= this.postService.getPostByUserId(userId);
        int number=  this.postService.numberOfPostPerUser(userId);
        response.put("status", "OK(200)");
        response.put("message", post);
        response.put("numberofPost", number);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    //get posts of in a particular cateogry
    @GetMapping("posts/category/{categoryId}")
    public ResponseEntity<?> getPostByCategory(@PathVariable("categoryId")String categoryId)
    {
        Map<String,Object> response=new HashMap<>();
        List<PostDTO> post= this.postService.getPostByCategoryId(categoryId);
        int number=this.postService.numberOfPostPerCategory(categoryId);
        response.put("status", "OK(200)");
        response.put("message", post);
        response.put("numberofPost", number);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
