package com.example.chatbot.business.abstracts;

import com.example.chatbot.entity.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface NewsService {

     void saveNew(MultipartFile multipartFile, String newsName, String newsHeader);

     List<News> getAllNews();
}
