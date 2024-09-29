package com.blogrestapi.Entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Document(collection = "role")
@Data
public class Role {
    @MongoId
    private int id;
    private String name;
}
