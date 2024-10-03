<<<<<<< HEAD
package com.blogrestapi.Entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @MongoId
    private int id;
    private String name;
}
=======
package com.blogrestapi.Entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @MongoId
    private int id;
    private String name;
}
>>>>>>> 4e1d3c33e347acf859fd8818dbc58cef4c7a60f3
