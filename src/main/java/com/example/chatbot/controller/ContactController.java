package com.example.chatbot.controller;

import com.example.chatbot.business.concrete.ContactServiceImpl;
import com.example.chatbot.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@Data
public class ContactController {
    private final ContactServiceImpl contactService;

    @PostMapping("/addContact")
    public String addContact(@RequestParam(value = "topic",required = false)String topic,@RequestParam(value = "content",required = false)String content){
        Contact contact=new Contact();
        contact.setTopic(topic);
        contact.setContent(content);
        contactService.addContact(contact);

        return "redirect:http://localhost:8080/mainPage/news";
    }
}
