package com.blogrestapi.Entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "database_sequence")
@Data
public class DatabaseSequence {
    private String _id;
    @Field(name = "seq")
    private long seq;
}
