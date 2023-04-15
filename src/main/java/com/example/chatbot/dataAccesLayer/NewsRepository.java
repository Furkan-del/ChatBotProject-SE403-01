package com.example.chatbot.dataAccesLayer;

import com.example.chatbot.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {

}
