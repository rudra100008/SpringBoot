package com.mongodbConnect.StudentRepo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodbConnect.Entity.Marks;
import com.mongodbConnect.Entity.Student;

public interface MarksRepo extends MongoRepository<Marks,Integer> {
    Marks findMarksByStudent(Student student);
}
