package com.blogrestapi.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    @NotEmpty(message = "Required")
    private String username;
    @Column(nullable = false)
    @NotEmpty(message = "Required")
    @Email(message = "Invalid email format")
    private String email;
    @Column(nullable = false)
    @NotEmpty(message = "Required")
    @Size(min=3,max=16,message = "Password should be minimum 3 and maximum 16 letters")
    private String password;
    private boolean isEnable;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
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
