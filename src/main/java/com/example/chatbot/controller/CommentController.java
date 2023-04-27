package com.example.chatbot.controller;

import com.example.chatbot.business.concrete.ChatGptServiceImpl;
import com.example.chatbot.business.concrete.CommentServiceImpl;
import com.example.chatbot.business.concrete.NewsServiceImpl;
import com.example.chatbot.entity.Comment;
import com.example.chatbot.entity.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Data
@AllArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentService;
    private final ChatGptServiceImpl chatGptService;
    private final NewsServiceImpl newsService;

    @GetMapping("mainPage/comments")
    public String getAllComments(Model model) {

        /* model.addAttribute("")
         */
        return "comment";
    }


    @PostMapping("mainPage/news/{id}")
    public String postComment(@ModelAttribute("commentsAll") Comment comment, @RequestParam("comment") String comments, @PathVariable Long id) {
        comment.setComment(comments);
        Date currentDate = new Date();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        comment.setDates(df.format(currentDate));
        String answer = chatGptService.generateText(comments);
        comment.setCommentType(answer);
        News news = newsService.getNewsById(id);
        comment.setNews(news);
        /* comment.setUserId(1L);*/
        commentService.add(comment);
        return "redirect:/mainPage/comments/{id}";
    }

    @GetMapping("mainPage/news/delete/{id}")
    public String delete(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);
        commentService.delete(comment);
        return "redirect:/mainPage/comments/{id}";
    }

    @GetMapping("mainPage/comments/{id}")
    public String getShowCommentById(@PathVariable Long id, Model model) {
        model.addAttribute("newsCommentForId", newsService.getNewsById(id));
        model.addAttribute("commentsAll", newsService.getNewsById(id).getCommentList());
        return "comment";
    }

}
