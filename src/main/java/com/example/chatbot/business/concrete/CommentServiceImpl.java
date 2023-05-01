package com.example.chatbot.business.concrete;

import com.example.chatbot.business.abstracts.CommentService;
import com.example.chatbot.dataAccesLayer.CommentRepository;
import com.example.chatbot.dataAccesLayer.NewsRepository;
import com.example.chatbot.entity.Comment;
import com.example.chatbot.entity.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class CommentServiceImpl implements CommentService {
    // dependecy Injection IOC Container is here active
private final CommentRepository commentRepository;
private  final  NewsRepository newsRepository;
@Override
    public List<Comment> getAllComments() {
        return  commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long id) {
        return  commentRepository.findById(id).orElseThrow();

    }

  /*  @Override
    public void add(Comment comment) {
        return null;
    }
*/
    @Override
    public void add(Comment comment,@PathVariable  Long id) {
        News news=newsRepository.findById(id).orElseThrow();
        comment.setNews(news);

        commentRepository.save(comment);


    }

    @Override
    public void delete(Comment comment) {
          commentRepository.delete(comment);
    }

 /*   @Override
    public void updateComment(Comment comment) {

    }
*/
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

   /* @Override
    public Comment getCommentsById(Long commentId, Long newsId) {
        Optional<Comment>comment=commentRepository.findById(commentId);
        if(comment.isPresent()&&comment.get().getNews().getId().equals(newsId)){
            return comment.get();
        }else{
            return null;
        }
    }*/

}
