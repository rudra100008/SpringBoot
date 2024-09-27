package com.blogrestapi.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.blogrestapi.DTO.PostDTO;

@Service
public interface PostService {

    //get all post
    List<PostDTO> getAllPost(int pageNumber,int pageSize);
    //get post by id
    PostDTO getPostById(String id);
    //save the post
    PostDTO createPost(PostDTO postDTO,String userId,String categoryId);
    //update the post(this will update the whole data if the data is send null in database the null will be saved)
    PostDTO updatePostById(String id,PostDTO postDTO,String userId,String categoryId);
    //delete the post
    void deletePostById(String id);
    //patch the post(to update the only required filed(like postTitle or content or image etc))
    PostDTO updatePostField(String id,PostDTO postDTO,String userId,String categoryId);
    //search post
    PostDTO searchPost(String keyword);
    //to get the post by userID
    List<PostDTO> getPostByUserId(String userId);
    //to get the post by cateforyId
    List<PostDTO> getPostByCategoryId(String categoryId);
    //get number of post in userID
    Integer numberOfPostPerUser(String userid);
    //get number of post in categoryId
    Integer  numberOfPostPerCategory(String categoryId);
}
