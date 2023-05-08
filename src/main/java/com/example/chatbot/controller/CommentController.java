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
    static Long idForSomeOperation;
    private final CommentServiceImpl commentService;
    private final ChatGptServiceImpl chatGptService;
    private final NewsServiceImpl newsService;

    @GetMapping("mainPage/comments")
    public String getAllComments() {

        /* model.addAttribute("")
         */
        return "comment";
    }

    @PostMapping("mainPage/news/{id}")
    public String postComment(@RequestParam("comment") String comments, @PathVariable Long id) {
        News news = newsService.getNewsById(id);
        if (news != null) {
            Comment comment1 = new Comment();
            comment1.setComment(comments);
            Date currentDate = new Date();
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            comment1.setDates(df.format(currentDate));
            String answer = chatGptService.generateText(comments);
            comment1.setCommentType(answer);
            comment1.setNews(news);
            commentService.add(comment1);
        }


        return "redirect:/mainPage/comments/{id}";
    }

    @GetMapping("mainPage/news/delete/{id}")
    public String delete(@PathVariable Long id) {

        Comment comment = commentService.getCommentById(id);
        commentService.delete(comment);
        return "redirect:/mainPage/comments/" +idForSomeOperation;
    }

    @GetMapping("mainPage/comments/{id}")
    public String getShowCommentById(@PathVariable Long id, Model model) {
        idForSomeOperation =id;
        model.addAttribute("newsCommentForId", newsService.getNewsById(id));
        model.addAttribute("commentsAll", newsService.getNewsById(id).getCommentList());
        return "comment";
    }

}
