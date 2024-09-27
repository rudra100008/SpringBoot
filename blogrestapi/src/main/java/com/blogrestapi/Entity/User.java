package com.blogrestapi.Entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @MongoId
    private String id;
    private String username;
    private String email;
    private String password;
    private boolean isEnable;
    @DBRef
    private List<Post> post = new ArrayList<>();
}
