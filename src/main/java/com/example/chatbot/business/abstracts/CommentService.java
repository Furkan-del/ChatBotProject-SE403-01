package com.example.chatbot.business.abstracts;

import com.example.chatbot.entity.Comment;

import java.util.List;

public interface CommentService {
     List<Comment> getAllComments();
     Comment getCommentById(Long id);
     void add(Comment comment);

     void delete(Comment comment);


     double calculateRate(List<Comment> comments);

}
