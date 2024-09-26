package com.mongodbConnect.StudentRepo;



import org.springframework.data.mongodb.repository.MongoRepository;
import com.mongodbConnect.Entity.Student;

public interface StudentRepo extends MongoRepository<Student,Integer> {
    
}
