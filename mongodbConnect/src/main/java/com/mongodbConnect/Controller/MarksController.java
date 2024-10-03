package com.mongodbConnect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodbConnect.Entity.Marks;
import com.mongodbConnect.Entity.Student;
import com.mongodbConnect.Service.SequenceGeneratorService;
import com.mongodbConnect.StudentRepo.MarksRepo;
import com.mongodbConnect.StudentRepo.StudentRepo;

@RestController
public class MarksController {
    @Autowired
    private MarksRepo marksRepo;
      @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private SequenceGeneratorService service;
    @PostMapping("/marks")
    public ResponseEntity<?> postMarks(@RequestBody Marks marks,@RequestParam("student")Student student)
    {
        marks.setId((int)service.generateSequence("marks_sequence"));
        marks.setStudent(student);
        Marks saveMarks=this.marksRepo.save(marks);
        return ResponseEntity.ok(saveMarks);
    }
    @GetMapping("/marks/student/{id}")
    public ResponseEntity<?> getMarksOfStudent(@PathVariable("id")int id)
    {
        Student student=this.studentRepo.findById(id).orElse(null);
        Marks marks =this.marksRepo.findMarksByStudent(student);
        return ResponseEntity.ok(marks);
    }
}
