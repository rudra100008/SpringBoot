package com.blogrestapi.ServiceImpl;

import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<PostDTO> getAllPost(int pageNumber,int pageSize) {
        // the value of pageNumber start form 0 in the database
        Pageable pageable=PageRequest.of(pageNumber,pageSize);
        Page<Post> page=this.postDao.findAll(pageable);
        List<Post> allPost=page.getContent();
        return allPost.stream().map(post -> modelMapper.map(post, PostDTO.class))
                .toList();
    }

    @Override
    public PostDTO getPostById(String id) {
        return this.postDao.findById(id)
                .map(post -> modelMapper.map(post, PostDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with  id: " + id));
    }

    @Override
    public PostDTO createPost(PostDTO postDTO, String userId, String categoryId) {
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
    public PostDTO updatePostById(String id, PostDTO postDTO, String userId, String categoryId) {
        Post post = this.postDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by userId: " + userId));
        Category category = this.categoryDao.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with categoryId: " + categoryId));

        post.setPostTitle(postDTO.getPostTitle());
        post.setContent(postDTO.getContent());
        post.setImage(postDTO.getImage() != null ? postDTO.getImage() : "default.jpg");
        post.setPostDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post updatePost = this.postDao.save(post);
        return modelMapper.map(updatePost, PostDTO.class);
    }

    @Override
    public void deletePostById(String id) {
        if (!this.postDao.existsById(id)) {
            throw new ResourceNotFoundException("Post not found with id: " + id);
        }
        this.postDao.deleteById(id);
    }

    @Override
    public PostDTO searchPost(String keyword) {
        throw new UnsupportedOperationException("Unimplemented method 'seacrhPost'");
    }

    @Override
    public PostDTO updatePostField(String id, PostDTO postDTO, String userId, String categoryId) {
        Post post = this.postDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by userId: " + userId));
        Category category = this.categoryDao.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with categoryId: " + categoryId));
        if (!postDTO.getPostTitle().isEmpty()) {
            post.setPostTitle(postDTO.getPostTitle());
        }  
        if (!postDTO.getContent().isEmpty()) {
            post.setContent(postDTO.getContent());
        }  
        if (postDTO.getImage()!=null) {
            post.setImage(postDTO.getImage());
        } else if(postDTO.getImage()==null)
        {
            post.setImage("default.jpg");
        }  
        if (postDTO.getCategoryId() != null && !postDTO.getCategoryId().equals(category.getCategoryId())) {
            Category newCategory = this.categoryDao.findById(postDTO.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with categoryId: " + postDTO.getCategoryId()));
            post.setCategory(newCategory);
        } else {
            post.setCategory(category);
        }
        post.setPostDate(new Date());
         post.setUser(user);
        Post updatePost = this.postDao.save(post);
        return modelMapper.map(updatePost, PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostByUserId(String userId) {
        User user =this.userDao.findById(userId)
        .orElseThrow(()->new ResourceNotFoundException("User not found by this id: "+userId));
        return this.postDao.findPostByUser(user).stream().map(post->modelMapper.map(post, PostDTO.class)).toList();
    }

    @Override
    public List<PostDTO> getPostByCategoryId(String categoryId) {
        Category category=this.categoryDao.findById(categoryId)
        .orElseThrow(()->new ResourceNotFoundException("Category not found by this id: "+categoryId));
       return this.postDao.findPostByCategory(category).stream().map(post->modelMapper.map(post, PostDTO.class)).toList();
    }
    public Integer  numberOfPostPerUser(String userid)
    {
        User user =this.userDao.findById(userid)
        .orElseThrow(()->new ResourceNotFoundException("User not found by this id: "+userid));
        return this.postDao.countByUser(user);
    }

    @Override
    public Integer numberOfPostPerCategory(String categoryId) {
       Category category=this.categoryDao.findById(categoryId)
       .orElseThrow(()->new ResourceNotFoundException("Category not found by this id: "+categoryId));
       return this.postDao.countByCategory(category);
    }

}
