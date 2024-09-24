package com.blogrestapi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogrestapi.DTO.PostDTO;

@Service
public interface PostService {

    //get all post
    List<PostDTO> getAllPost();
    //get post by id
    PostDTO getPostById(int id);
    //save the post
    PostDTO createPost(PostDTO postDTO,int userId,int categoryId);
    //update the post
    PostDTO updatePostById(int id,PostDTO postDTO,int userId,int categoryId);
    //delete the post
    void deletePostById(int id);

    //search post
    PostDTO searchPost(String keyword);
}
