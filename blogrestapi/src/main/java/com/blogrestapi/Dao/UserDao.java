package com.blogrestapi.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.blogrestapi.Entity.User;
@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    @Query("Select u from User as u where u.username=:username")
    User findUserByUsername(@Param("username")String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
