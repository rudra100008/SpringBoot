<<<<<<< HEAD
package com.blogrestapi.Service;


import java.util.List;

import org.springframework.stereotype.Service;
import com.blogrestapi.DTO.CommentDTO;

@Service
public interface CommentService {
    List<CommentDTO> getAllComments();
    CommentDTO findCommentById(int commentId);
    CommentDTO createComments(CommentDTO commentDTO,int userId,int postId);
    CommentDTO updateComment(int commentId,CommentDTO commentDTO,int userId,int postId);
    void deleteComment(int commentId);
}
=======
package com.blogrestapi.Service;


import java.util.List;

import org.springframework.stereotype.Service;
import com.blogrestapi.DTO.CommentDTO;

@Service
public interface CommentService {
    List<CommentDTO> getAllComments();
    CommentDTO findCommentById(int commentId);
    CommentDTO createComments(CommentDTO commentDTO,int userId,int postId);
    CommentDTO updateComment(int commentId,CommentDTO commentDTO,int userId,int postId);
    void deleteComment(int commentId);
}
>>>>>>> 4e1d3c33e347acf859fd8818dbc58cef4c7a60f3
