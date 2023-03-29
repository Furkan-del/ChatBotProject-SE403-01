package com.example.chatbot.business.abstracts;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

public interface ChatGptService {
    public String generateText( @RequestParam("prompt") String prompt);
}
