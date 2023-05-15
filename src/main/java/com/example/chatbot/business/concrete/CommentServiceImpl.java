package com.example.chatbot.business.concrete;

import com.example.chatbot.business.abstracts.CommentService;
import com.example.chatbot.dataAccesLayer.CommentRepository;
import com.example.chatbot.dataAccesLayer.NewsRepository;
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
    private final NewsRepository newsRepository;
    private final NewsServiceImpl newsService;

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow();
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
    public double calculateRate(Long id) {
        List<Comment> comments;
        comments = newsService.getNewsById(id).getCommentList();
        double counterPositive = 0;
        double counterNegative = 0;
        double rate;
        String commentType;
        for (int i = 0; i < comments.size(); i++) {
            commentType = comments.get(i).getCommentType();
            if (commentType.equals("\n\nNegative")) {
                counterNegative += 1.0;
            } else {
                counterPositive += 1.0;
            }
        }
        rate = (counterPositive / (counterPositive + counterNegative)) * 100;
        if (Double.isNaN(rate)) {
            rate = 0;
        } else if (rate == 0) {
            rate = 0;
        }
        return rate;
    }


    /*@Override
    public void deleteCommentById(Long newId, Long commentId) {
        Optional<Comment>comment=commentRepository.findById(commentId);
        if(comment.isPresent()&& comment.get().getNews().getId().equals(newId)){
            commentRepository.delete(comment.get());
        }else{
            System.out.println("Value is not present");
        }
    }
*/
/*    @Override
    public void postCommentById(Long commentId, Long newId) {
        Optional<Comment>comment=commentRepository.findById(commentId);
        if(comment.isPresent()&&comment.get().getNews().getId().equals(newId)){
            commentRepository.save(comment.get());
        }

    }*/

}
