<<<<<<< HEAD
package com.blogrestapi.Dao;



import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blogrestapi.Entity.User;
@Repository
public interface UserDao extends MongoRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
=======
package com.blogrestapi.Dao;



import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blogrestapi.Entity.User;
@Repository
public interface UserDao extends MongoRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
>>>>>>> 4e1d3c33e347acf859fd8818dbc58cef4c7a60f3
