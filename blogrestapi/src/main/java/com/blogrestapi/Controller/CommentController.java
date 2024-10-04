package com.blogrestapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blogrestapi.DTO.CommentDTO;
import com.blogrestapi.Service.CommentService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comments/user/{userId}/post/{postId}")
    public ResponseEntity<CommentDTO> createComment(
        @RequestBody CommentDTO commentDTO,
        @PathVariable int userId,
        @PathVariable int postId
    ) {
        CommentDTO comment = this.commentService.createComments(commentDTO, userId, postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PutMapping("/comments/{commentId}/user/{userId}/post/{postId}")
    public ResponseEntity<CommentDTO> updateComment(
        @PathVariable int commentId,
        @RequestBody CommentDTO commentDTO,
        @PathVariable int userId,
        @PathVariable int postId
    ) {
        CommentDTO updateCommentDTO = this.commentService.updateComment(commentId, commentDTO, userId, postId);
        return ResponseEntity.ok().body(updateCommentDTO);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId) {
        CommentDTO commentDTO = this.commentService.findCommentById(commentId);
        this.commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commentDTO); // 204 No Content
    }
}
