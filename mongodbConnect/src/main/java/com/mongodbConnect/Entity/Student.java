package com.mongodbConnect.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "students")
public class Student {
    @Id
    private int id;
    private String name;
    private String collegName;
    private String rollNo;
    
    @DBRef
    private Marks marks; // Change here

    public Student() {
    }

    public Student(int id, String name, String collegName, String rollNo) {
        this.id = id;
        this.name = name;
        this.collegName = collegName;
        this.rollNo = rollNo;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollegName() {
        return collegName;
    }

    public void setCollegName(String collegName) {
        this.collegName = collegName;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public Marks getMarks() {
        return marks; // Add getter for marks
    }

    public void setMarks(Marks marks) {
        this.marks = marks; // Add setter for marks
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", collegName=" + collegName + ", rollNo=" + rollNo + ", marks="
                + marks + "]";
    }
    
}
