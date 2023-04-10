package com.example.chatbot.controller;

import com.example.chatbot.business.concrete.ChatGptServiceImpl;
import com.example.chatbot.business.concrete.CommentServiceImpl;

import com.example.chatbot.business.concrete.UserServiceImpl;
import com.example.chatbot.entity.Comment;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Data
@AllArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentService;
    private final ChatGptServiceImpl chatGptService;



    @GetMapping("mainPage/comments")
    public String getAllComments(Model model) {
        model.addAttribute("commentsAll", commentService.getAllComments());
        return "comment";
    }



    @PostMapping("/comments")
    public String postComment(@ModelAttribute("commentsAll") Comment comment, @RequestParam("comment") String comments) {
        comment.setComment(comments);
        Date currentDate = new Date();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        comment.setDates(df.format(currentDate));
        String answer=chatGptService.generateText(comments);
        comment.setCommentType(answer);
       /* comment.setUserId(1L);*/
        commentService.add(comment);
        return "redirect:/mainPage/comments";
    }

    @GetMapping("mainPage/comments/delete/{id}")
    public  String delete(@PathVariable Long id){
        Comment comment=commentService.getCommentById(id);
        commentService.delete(comment);
        return "redirect:/comments";
    }

}
