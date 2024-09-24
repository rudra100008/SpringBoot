package com.blogrestapi.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogrestapi.DTO.PostDTO;
import com.blogrestapi.Dao.CategoryDao;
import com.blogrestapi.Dao.PostDao;
import com.blogrestapi.Dao.UserDao;
import com.blogrestapi.Entity.Category;
import com.blogrestapi.Entity.Post;
import com.blogrestapi.Entity.User;
import com.blogrestapi.Exception.ResourceNotFoundException;
import com.blogrestapi.Service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<PostDTO> getAllPost() {
        return this.postDao.findAll().stream().map(post -> modelMapper.map(post, PostDTO.class))
                .toList();
    }

    @Override
    public PostDTO getPostById(int id) {
        return this.postDao.findById(id)
                .map(post -> modelMapper.map(post, PostDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with  id: " + id));
    }

    @Override
    public PostDTO createPost(PostDTO postDTO, int userId, int categoryId) {
        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by userId: " + userId));
        Category category = this.categoryDao.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with categoryId: " + categoryId));

        Post post = modelMapper.map(postDTO, Post.class);
        post.setImage(postDTO.getImage() != null ? postDTO.getImage() : "default.jpg");
        post.setPostDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = this.postDao.save(post);
        return modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePostById(int id, PostDTO postDTO, int userId, int categoryId) {
        Post post = this.postDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
         this.userDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by userId: " + userId));
         this.categoryDao.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with categoryId: " + categoryId));

        post.setPostTitle(postDTO.getPostTitle());
        post.setContent(postDTO.getContent());
        post.setImage(postDTO.getImage()!=null?postDTO.getImage():"default.jpg");
        post.setPostDate(new Date());
        Post updatePost = this.postDao.save(post);
        return modelMapper.map(updatePost, PostDTO.class);
    }

    @Override
    public void deletePostById(int id) {
        if (!this.postDao.existsById(id)) {
            throw new ResourceNotFoundException("Post not found with id: " + id);
        }
        this.postDao.deleteById(id);
    }

    @Override
    public PostDTO searchPost(String keyword) {
        throw new UnsupportedOperationException("Unimplemented method 'seacrhPost'");
    }

}
