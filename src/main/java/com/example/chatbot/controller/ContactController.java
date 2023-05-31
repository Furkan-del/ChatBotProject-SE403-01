package com.example.chatbot.controller;

import com.example.chatbot.business.abstracts.ContactService;
import com.example.chatbot.business.abstracts.UserService;
import com.example.chatbot.business.concrete.ContactServiceImpl;
import com.example.chatbot.business.concrete.UserServiceImpl;
import com.example.chatbot.entity.Contact;
import com.example.chatbot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
@AllArgsConstructor
@Data
public class ContactController {
    private final ContactService contactService;
    private final UserService userService;


    @PostMapping("/addContact")
    public String addContact(@RequestParam(value = "topic", required = false) String topic, @RequestParam(value = "content", required = false) String content, HttpSession httpSession) {
        Contact contact = new Contact();
        contact.setTopic(topic);
        contact.setContent(content);
       String userName=httpSession.getAttribute("username").toString();
       User user=userService.findByUsername(userName);
       contact.setUser(user);
       contact.setDate(LocalDate.now());
        contactService.addContact(contact);
        return "redirect:http://localhost:8080/mainPage/news";
    }


}
