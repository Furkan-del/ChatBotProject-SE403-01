package com.example.chatbot.business.concrete;

import com.example.chatbot.business.abstracts.NewsService;
import com.example.chatbot.dataAccesLayer.NewsRepository;
import com.example.chatbot.entity.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Override
    public void addNew(News news) {
        newsRepository.save(news);
    }

    @Override
    public void deleteNews(News news) {

    }
}
