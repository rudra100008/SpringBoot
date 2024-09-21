package com.blogrestapi.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postId;
    private String postTitle;
    private String content;
    private String image;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;
   
}
