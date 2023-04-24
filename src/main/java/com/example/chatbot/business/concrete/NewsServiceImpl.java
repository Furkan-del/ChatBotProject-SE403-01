package com.example.chatbot.business.concrete;

import com.example.chatbot.business.abstracts.NewsService;
import com.example.chatbot.dataAccesLayer.NewsRepository;
import com.example.chatbot.entity.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Data
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;


    @Override
    public void saveNew(MultipartFile multipartFile, String newsName, String newsHeader) {
        News news=new News();
        String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename()); //dosya adını temizler kaydeder
        if(fileName.contains("..")){
            System.out.println("Not a valid file");
        }
        try {
            news.setPhoto(Base64.getEncoder().encodeToString(multipartFile.getBytes())); //stringe dönüştürür
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        news.setNewsContent(newsName);
        news.setNewsHeader(newsHeader);
        newsRepository.save(news);
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News getNewsById(Long id) {
        News news=newsRepository.findById(id).orElseThrow();
        return news;
    }

}
