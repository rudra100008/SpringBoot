package com.company.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="Author_Details")
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int authorID;
    @Column(name="First_Name")
    private String authorFirstName;
    @Column(name="Last_Name")
    private String authorLastName;

    @OneToOne(mappedBy="author",cascade=CascadeType.ALL)
    private Book book;
   
    public Author(){
        super();
    }
    
    public Author(int authorID, String authorFirstName, String authorLastName) {
        this.authorID = authorID;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
       
    }
    public int getAuthorID() {
        return authorID;
    }
    public String getAuthorFirstName() {
        return authorFirstName;
    }
    public String getAuthorLastName() {
        return authorLastName;
    }
    
    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }
    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }
    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }
    
    
}
