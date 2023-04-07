package com.example.chatbot.business.abstracts;

import com.example.chatbot.entity.User;

public interface UserService {
    void saveUser(User user);

    User getUserById(Long id);
}
