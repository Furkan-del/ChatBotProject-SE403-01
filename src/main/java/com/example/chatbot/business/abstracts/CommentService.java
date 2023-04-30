package com.example.chatbot.business.abstracts;

import com.example.chatbot.entity.Comment;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CommentService {
     List<Comment> getAllComments();
     Comment getCommentById(Long id);
     void add(Comment comment,@PathVariable Long id);

     void delete(Comment comment);
/*
     void updateComment(Comment comment);
    void deleteCommentById(Long newId,Long commentId);
    void postCommentById(Long commentId,Long newId);

    Comment getCommentsById(Long commentId,Long newsId);*/

}
