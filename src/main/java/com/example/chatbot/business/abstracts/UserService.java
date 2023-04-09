package com.example.chatbot.business.abstracts;


import com.example.chatbot.entity.User;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface UserService {
    void saveUser(User user);

    User getUserById(Long id);



    boolean checkUser(String userName, @ModelAttribute("user")User user);


}