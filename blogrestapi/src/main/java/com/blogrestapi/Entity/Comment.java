<<<<<<< HEAD
package com.blogrestapi.Entity;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class Comment {
    @MongoId
    private int commentId;
    private String comments;
    @DBRef
    private Post post;
    @DBRef
    private User user;
}
=======
package com.blogrestapi.Entity;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class Comment {
    @MongoId
    private int commentId;
    private String comments;
    @DBRef
    private Post post;
    @DBRef
    private User user;
}
>>>>>>> 4e1d3c33e347acf859fd8818dbc58cef4c7a60f3
