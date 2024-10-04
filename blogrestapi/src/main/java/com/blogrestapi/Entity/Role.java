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
