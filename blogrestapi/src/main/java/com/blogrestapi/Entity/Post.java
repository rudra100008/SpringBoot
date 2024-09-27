package com.blogrestapi.Entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "posts")
public class Post {
    @MongoId
    private String postId;
    
    private String postTitle;
    
    private String content;
    private String image;
    private Date postDate;
    @DBRef
    private User user;
    @DBRef
    private Category category;

   
}
