package com.example.chatbot.controller;

import com.example.chatbot.business.concrete.CommentServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;

@Controller
@Data
@AllArgsConstructor
public class CommentController {
private  final CommentServiceImpl commentService;



}
