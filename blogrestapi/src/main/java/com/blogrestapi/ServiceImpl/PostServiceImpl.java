package com.blogrestapi.ServiceImpl;

import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogrestapi.DTO.PageResponse;
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
    @Autowired
    private SequenceGeneratorService sequence;

    @Override
    public PageResponse<PostDTO> getAllPost(int pageNumber,int pageSize,String sortBy,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase("ascending")
        ? Sort.by(sortBy).ascending()
        : Sort.by(sortBy).descending();
       
        // the value of pageNumber start form 0 in the database
        Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> page=this.postDao.findAll(pageable);
        List<Post> allPost=page.getContent();
        List<PostDTO> getAllPost= allPost.stream().map(post -> modelMapper.map(post, PostDTO.class)).toList();
        long totalElement=page.getTotalElements();
        int totalPage=page.getTotalPages();
        boolean lastPage=page.isLast();
        PageResponse<PostDTO> pageResponse=new PageResponse<>(
            "OK(200)",
            getAllPost,
            pageSize,
            pageNumber,
            totalPage,
            totalElement,
            lastPage
        );
        return pageResponse;
    }

    @Override
    public PostDTO getPostById(int id) {
        return this.postDao.findById(id)
                .map(post -> modelMapper.map(post, PostDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with  id: " + id));
    }

    @Override
    public PostDTO createPost(PostDTO postDTO, int userId, int categoryId) {
        postDTO.setPostId((int)sequence.generateSequence("post_sequence"));
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
    public void deletePostById(int id) {
        if (!this.postDao.existsById(id)) {
            throw new ResourceNotFoundException("Post not found with id: " + id);
        }
        this.postDao.deleteById(id);
    }

    @Override
    public List<PostDTO> searchPost(String keyword) {
        List<Post> listPost =this.postDao.findByPostTitleContainingIgnoreCase(keyword);
        return listPost.stream().map(p->modelMapper.map(p, PostDTO.class)).toList();
    }

    @Override
    public PostDTO updatePostField(int id, PostDTO postDTO, int userId, int categoryId) {
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
        if ( postDTO.getCategoryId()!=category.getCategoryId() && postDTO.getCategoryId() !=0) {
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
    public PageResponse<PostDTO> getPostByUserId(int userId,int pageNumber,int pageSize,String sortBy,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase("ascending")
        ?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        User user =this.userDao.findById(userId)
        .orElseThrow(()->new ResourceNotFoundException("User not found by this id: "+userId));
        Pageable pageable=PageRequest.of(pageNumber, pageSize,sort);
        Page<Post> page=this.postDao.findPostByUser(user,pageable);
        List<PostDTO> allPost=page.getContent().stream().map(
            e->modelMapper.map(e, PostDTO.class)
        ).toList();
        long totalElement=page.getTotalElements();
        int totalPage=page.getTotalPages();
        boolean lastPage=page.isLast();
        PageResponse<PostDTO> pageResponse=new PageResponse<>(
            "OK(200)",
            allPost,
            pageSize,
            pageNumber,
            totalPage,
            totalElement,
            lastPage
        );
        return pageResponse;
    }

    @Override
    public PageResponse<PostDTO> getPostByCategoryId(int categoryId,int pageNumber,int pageSize,String sortBy,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase("ascending")
        ?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        //to get the cateogry with providec categoryID
        Category category=this.categoryDao.findById(categoryId)
        .orElseThrow(()->new ResourceNotFoundException("Category not found by this id: "+categoryId));

        Pageable pageable=PageRequest.of(pageNumber, pageSize,sort);
        Page<Post> pagePost=this.postDao.findPostByCategory(category,pageable);
        List<PostDTO> allPost=pagePost.getContent().stream()
        .map(post->modelMapper.map(pagePost, PostDTO.class)).toList();

        long totalElement=pagePost.getTotalElements();
        int totalPage=pagePost.getTotalPages();
        boolean lastPage=pagePost.isLast();
        PageResponse<PostDTO> pageResponse=new PageResponse<>(
            "Ok(200)",
            allPost,
            pageSize,
            pageNumber,
            totalPage,
            totalElement,
            lastPage
        );
       return pageResponse;
    }
}
