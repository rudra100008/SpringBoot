package com.mongodbConnect.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// This document will be used to store sequence information
@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;  // Holds the name of the sequence (like "student_sequence")
    private long seq;   // Holds the current value of the sequence

    public DatabaseSequence() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }
}
