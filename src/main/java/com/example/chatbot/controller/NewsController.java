package com.example.chatbot.controller;

import com.example.chatbot.business.abstracts.ContactService;
import com.example.chatbot.business.abstracts.NewsService;
import com.example.chatbot.business.concrete.ContactServiceImpl;
import com.example.chatbot.business.concrete.NewsServiceImpl;

import com.example.chatbot.entity.News;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@AllArgsConstructor
@Data
public class NewsController {
    private final NewsService newsService;
    private final ContactService contactService;
    @PostMapping("/addNew")
    public String addNew(@RequestParam(value = "file", required = false) MultipartFile multipartFile, @RequestParam(value = "newsContent", required = false) String newsName, @RequestParam(value = "newsHeader", required = false) String newsHeader) {
        newsService.saveNew(multipartFile, newsName, newsHeader);
        return "redirect:/mainPage/news";
    }
    @GetMapping("/mainPage/news")
    public String getAllNew(Model model) {
        List<News> newsList = newsService.getAllNews();
        model.addAttribute("newImageList", newsList);
        model.addAttribute("responseData",ChatGptController.answer);

        return "indexes";
    }
    @GetMapping("/admin")
    public String showAddNew(Model model) {
        model.addAttribute("allContacts", contactService.getAll());
        return "admin";
    }
    @GetMapping("/admin/contacts/{id}")
    public String deleteMessage(@PathVariable Long id){
        contactService.deleteById(id);
        return "redirect:/admin";
    }

}
