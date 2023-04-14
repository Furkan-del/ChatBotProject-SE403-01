package com.example.chatbot.controller;

import com.example.chatbot.business.abstracts.NewsService;
import com.example.chatbot.business.concrete.NewsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Controller
@AllArgsConstructor
@Data
public class NewsController {
    private final NewsServiceImpl newsService;

    @PostMapping("/addNew")
    public String addNew(@RequestParam("file") MultipartFile multipartFile, @RequestParam("newsName") String newsName,String newsHeader) {
        newsService.saveNew(multipartFile, newsName, newsHeader);
        return "redirect:/mainPage";

    }
/*

    @RequestMapping(value = "/mainPage",method = RequestMethod.GET)
    public String getAllNew(Model model) {
        model.addAttribute("news", newsService.getAllNews());
        return "indexes";
    }
*/

    @GetMapping("/admin")
    public String showAddNew() {
        return "addProduct";
    }


}
