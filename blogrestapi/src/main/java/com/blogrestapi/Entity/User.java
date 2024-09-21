package com.blogrestapi.Entity;

import java.util.ArrayList;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String password;
    private boolean isEnable;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Post> post=new ArrayList<>();
}
