package com.blogrestapi.ServiceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogrestapi.DTO.CommentDTO;
import com.blogrestapi.Dao.CommentDao;
import com.blogrestapi.Dao.PostDao;
import com.blogrestapi.Dao.UserDao;
import com.blogrestapi.Entity.Comment;
import com.blogrestapi.Entity.Post;
import com.blogrestapi.Entity.User;
import com.blogrestapi.Exception.ResourceNotFoundException;
import com.blogrestapi.Exception.UnauthorizedException;
import com.blogrestapi.Service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PostDao postDao;
    @Autowired
    private SequenceGeneratorService sequence;

    @Override
    public CommentDTO createComments(CommentDTO commentDTO, int userId, int postId) {
       this.userDao.findById(userId)
      .orElseThrow(()->new ResourceNotFoundException("User not found by id: "+userId));
       this.postDao.findById(postId)
      .orElseThrow(()->new ResourceNotFoundException("Post not found by id: "+postId));
      commentDTO.setUserId(userId);
      commentDTO.setPostId(postId);
      commentDTO.setId((int)sequence.generateSequence("comment_sequence"));
      Comment comment=modelMapper.map(commentDTO, Comment.class);
      Comment savedComment=this.commentDao.save(comment);
      return modelMapper.map(savedComment, CommentDTO.class);
    }

    @Override
    public CommentDTO updateComment(int commentId, CommentDTO commentDTO, int userId, int postId) {
       Comment existingComment=this.commentDao.findById(commentId)
       .orElseThrow(()->new ResourceNotFoundException("Comment not found By id: "+commentId));
       User user =this.userDao.findById(userId)
       .orElseThrow(()->new ResourceNotFoundException("User not found by id: "+userId));
       Post post=this.postDao.findById(postId)
       .orElseThrow(()->new ResourceNotFoundException("Post not found by id: "+postId));
       if (existingComment.getUser().getId()!= user.getId()) {
        throw new UnauthorizedException("The user("+user.getUsername()+") cannot change the comment ");
       } 
       if (existingComment.getPost().getPostId() != post.getPostId()) {
        throw new UnauthorizedException("You cannnot change the comment of  Post("+post.getPostTitle()+")");
       }
       commentDTO.setId(commentId);
       commentDTO.setPostId(postId);
       commentDTO.setUserId(userId);
        modelMapper.map(commentDTO, existingComment);
       Comment savedComment=this.commentDao.save(existingComment);
       return modelMapper.map(savedComment, CommentDTO.class);

    }

    @Override
    public void deleteComment(int commentId) {
        if (!this.commentDao.existsById(commentId)) {
            throw new ResourceNotFoundException("Comment not found with id: "+commentId);
        }
        this.commentDao.deleteById(commentId);
    }

    @Override
    public List<CommentDTO> getAllComments() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllComments'");
    }

    @Override
    public CommentDTO findCommentById(int commentId) {
       return this.commentDao.findById(commentId).map(comment->modelMapper.map(comment, CommentDTO.class))
       .orElseThrow(()->new ResourceNotFoundException("Comment not found with id: "+commentId));
    }
    
}
