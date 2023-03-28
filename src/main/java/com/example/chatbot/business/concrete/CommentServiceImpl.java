package com.example.chatbot.business.concrete;

import com.example.chatbot.business.abstracts.CommentService;
import com.example.chatbot.dataAccesLayer.CommentRepository;
import com.example.chatbot.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Data
public class CommentServiceImpl implements CommentService {
    // dependecy Injection IOC Container is here active
private final CommentRepository commentRepository;

    @Override
    public List<Comment> getAllComments() {


        return  commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long id) {
        return  commentRepository.findById(id).orElseThrow();

    }

    @Override
    public void add(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
          commentRepository.delete(comment);
    }

    @Override
    public void updateComment(Comment comment) {

    }
}
