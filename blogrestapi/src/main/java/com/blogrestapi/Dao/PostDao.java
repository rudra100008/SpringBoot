package com.blogrestapi.Dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blogrestapi.Entity.Category;
import com.blogrestapi.Entity.Post;
import com.blogrestapi.Entity.User;

@Repository
public interface PostDao extends JpaRepository<Post,Integer> {
    List<Post> findPostByUser(User user);
    List<Post> findPostByCategory(Category category);
    int countByUser(User user);
    int countByCategory(Category Catrgory);
}
