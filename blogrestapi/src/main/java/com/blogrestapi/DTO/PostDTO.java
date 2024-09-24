package com.blogrestapi.DTO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private int postId;
    private String postTitle;
    private String content;
    private String image;
    private Date postDate;
    private int userId;
    private int categoryId;
    
}
