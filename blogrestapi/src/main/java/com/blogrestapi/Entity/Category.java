<<<<<<< HEAD
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
    
=======
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
    
>>>>>>> 4e1d3c33e347acf859fd8818dbc58cef4c7a60f3
}