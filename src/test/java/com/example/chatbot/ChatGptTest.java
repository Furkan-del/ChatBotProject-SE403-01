package com.example.chatbot;

import com.example.chatbot.business.concrete.ChatGptServiceImpl;
import com.example.chatbot.business.concrete.CommentServiceImpl;
import com.example.chatbot.business.concrete.NewsServiceImpl;
import com.example.chatbot.business.concrete.UserServiceImpl;
import com.example.chatbot.dataAccesLayer.NewsRepository;
import com.example.chatbot.dataAccesLayer.UserRepository;
import com.example.chatbot.entity.Comment;
import com.example.chatbot.entity.News;
import com.example.chatbot.entity.User;
import org.hibernate.validator.internal.constraintvalidators.bv.AssertTrueValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatGptTest {
   /* @Autowired
    private ChatGptServiceImpl chatGptService;

    @Autowired
    private NewsServiceImpl newsService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    public void testMyChatGptMethod() {

        String result = chatGptService.generateText("This is bad");
        assert result.equals("\n\nNegative");

    }

    @Test
    public void userFindByNameMethodTest() {
        var id = userServiceImpl.findByUsername("cagili").getId();
        assert id == 3;

    }*/

    @Mock
    private NewsRepository newsRepository;
    private NewsServiceImpl underTest;

    @Test
    public void canGetAllNews() {
        MockitoAnnotations.openMocks(this); //activate the mock objects
        underTest = new NewsServiceImpl(newsRepository);

        underTest.getAllNews();
    }

}
