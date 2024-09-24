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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogrestapi.DTO.PostDTO;
import com.blogrestapi.Service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public ResponseEntity<?> getAllPost() {
        List<PostDTO> post = this.postService.getAllPost();
        return ResponseEntity.ok(post);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") int id) {
        PostDTO postDTO = this.postService.getPostById(id);
        return ResponseEntity.ok(postDTO);
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO,
            @RequestParam("userId") int userId,
            @RequestParam("categoryId") int categoryId) 
    {
        PostDTO savedPost=this.postService.createPost(postDTO, userId, categoryId);
        return ResponseEntity.ok(savedPost);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") int id,
    @RequestBody PostDTO postDTO,
    @RequestParam("userId")int userId,
    @RequestParam("categoryId")int categoryId)
    {
        PostDTO updatePost=this.postService.updatePostById(id, postDTO, userId, categoryId);
        return ResponseEntity.ok(updatePost);
    }
    @DeleteMapping("post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id")int id)
    {
        this.postService.deletePostById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
