package com.example.chatbot.controller;

import com.example.chatbot.business.concrete.ChatGptServiceImpl;
import com.example.chatbot.business.concrete.CommentServiceImpl;
import com.example.chatbot.business.concrete.NewsServiceImpl;
import com.example.chatbot.entity.Comment;
import com.example.chatbot.entity.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
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

    static double rate;
    private final CommentServiceImpl commentService;
    private final ChatGptServiceImpl chatGptService;
    private final NewsServiceImpl newsService;

    @GetMapping("mainPage/comments")
    public String getAllComments() {

        /* model.addAttribute("")
         */
        return "comment";
    }

    @PostMapping("mainPage/news/{id}/postComment")
    public String postComment(@RequestParam("comment") String comments, @PathVariable("id") Long id) {

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
            rate=commentService.calculateRate(commentService.getAllComments());
        }
        return "redirect:/mainPage/news/{id}/comments";
    }

    @GetMapping("mainPage/news/{newsId}/delete/{commentId}")
    public String delete(@PathVariable(name = "newsId") Long newsId, @PathVariable("commentId") Long commentId) {
        Comment comment = commentService.getCommentById(commentId);
        commentService.delete(comment);
        return "redirect:/mainPage/news/" + newsId + "/comments";
    }

    @GetMapping("mainPage/news/{id}/comments")
    public String getShowCommentById(@PathVariable("id") Long id, @NotNull Model model) {
        model.addAttribute("newsCommentForId", newsService.getNewsById(id));
        model.addAttribute("commentsAll", newsService.getNewsById(id).getCommentList());
        model.addAttribute("rate",rate);
        return "comment";
    }

}
