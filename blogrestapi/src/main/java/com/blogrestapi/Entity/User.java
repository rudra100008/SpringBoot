package com.blogrestapi.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String email;
    private String password;
    private boolean isEnable;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> post;
    public User(){
        super();
    }
    public User(int id,String username,String email,String password,boolean isEnable,List<Post> post){
        this.id=id;
        this.username=username;
        this.email=email;
        this.password=password;
        this.isEnable=isEnable;
        this.post=post;
    }
}
