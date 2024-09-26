package com.mongodbConnect.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "marks")
public class Marks {
    @Id
    private int id;
    private double Math;
    private double Science;
    private double Social;
    private double English;
    @DBRef
    private Student student;
    public Marks() {
    }
    public Marks(int id, double math, double science, double social, double english, Student student) {
        this.id = id;
        Math = math;
        Science = science;
        Social = social;
        English = english;
        this.student = student;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getMath() {
        return Math;
    }
    public void setMath(double math) {
        Math = math;
    }
    public double getScience() {
        return Science;
    }
    public void setScience(double science) {
        Science = science;
    }
    public double getSocial() {
        return Social;
    }
    public void setSocial(double social) {
        Social = social;
    }
    public double getEnglish() {
        return English;
    }
    public void setEnglish(double english) {
        English = english;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    @Override
    public String toString() {
        return "Marks [id=" + id + ", Math=" + Math + ", Science=" + Science + ", Social=" + Social + ", English="
                + English + ", student=" + student + "]";
    }
    
}
