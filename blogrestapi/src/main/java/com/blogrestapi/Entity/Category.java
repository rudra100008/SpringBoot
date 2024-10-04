package com.blogrestapi.Entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "categorys")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @MongoId
    private int categoryId;
    private String categoryTitle;
    @DBRef
    private List<Post> post=new ArrayList<>();
    
}