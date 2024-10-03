package com.blogrestapi.DTO;

import lombok.Data;
@Data
public class CommentDTO {
    private int id;
    private String comments;
    private int postId;
    private  int  userId;
}
