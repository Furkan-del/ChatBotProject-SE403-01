package com.example.chatbot.business.abstracts;

import com.example.chatbot.entity.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> getAllComments();
    public Comment getCommentById(Long id);

    public void add(Comment comment);

    public void delete(Comment comment);

    public void updateComment(Comment comment);

}
