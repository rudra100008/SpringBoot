package com.mongodbConnect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodbConnect.Entity.Student;
import com.mongodbConnect.Service.SequenceGeneratorService;
import com.mongodbConnect.StudentRepo.StudentRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MainController {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private SequenceGeneratorService service;
    @GetMapping("/students")
    public ResponseEntity<?> getStudent()
    {
        List<Student> students=this.studentRepo.findAll();
        return ResponseEntity.ok(students);
    }
    @PostMapping("/students")
    public ResponseEntity<?> postMethodName(@RequestBody Student student) {
        student.setId((int) service.generateSequence("student_sequence"));
        Student saveStudent =this.studentRepo.save(student);
        return ResponseEntity.ok(saveStudent);
    }
    
}
