package com.blogrestapi.Dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.blogrestapi.Entity.Category;
import com.blogrestapi.Entity.Post;
import com.blogrestapi.Entity.User;

@Repository
public interface PostDao extends MongoRepository<Post,Integer> {
    Page<Post> findPostByUser(User user,Pageable pageable);
    List<Post> findPostByUser(User user);
    Page<Post> findPostByCategory(Category category,Pageable pageable);
    List<Post> findByPostTitleContainingIgnoreCase(String postTitle);

}
