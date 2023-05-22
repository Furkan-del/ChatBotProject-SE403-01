package com.example.chatbot;

import com.example.chatbot.business.concrete.ChatGptServiceImpl;
import com.example.chatbot.business.concrete.CommentServiceImpl;
import com.example.chatbot.business.concrete.NewsServiceImpl;
import com.example.chatbot.business.concrete.UserServiceImpl;
import com.example.chatbot.entity.Comment;
import com.example.chatbot.entity.News;
import com.example.chatbot.entity.User;
import org.hibernate.validator.internal.constraintvalidators.bv.AssertTrueValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatGptTest {
    @Autowired
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
        assert id==3;

    }


    @Test
    public void userCheckNullTest(){
        String nameOfAdmin="admin";
        User user=userServiceImpl.findByUsername(nameOfAdmin);
        userServiceImpl.checkUser(nameOfAdmin,user);
    }
}
