package com.blogrestapi.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
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
    public Post() {
    }
    public Post(int postId, String postTitle, String content, String image, User user) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.content = content;
        this.image = image;
        this.user = user;
    }
}
