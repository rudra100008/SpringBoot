package com.blogrestapi.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    @Column(nullable=false)
    private String postTitle;
    @Column(length = 10000,nullable = false)
    private String content;
    private String image;
    private Date postDate;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

   
}
