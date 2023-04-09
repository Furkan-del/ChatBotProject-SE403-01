package com.example.chatbot.business.concrete;

import com.example.chatbot.business.abstracts.UserService;
import com.example.chatbot.dataAccesLayer.AdminRepository;
import com.example.chatbot.dataAccesLayer.UserRepository;
import com.example.chatbot.entity.Admin;
import com.example.chatbot.entity.News;
import com.example.chatbot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;


@Service
@AllArgsConstructor
@Data
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;


    @Override
    public void saveUser(User user) {
        userRepository.save(user);

    }

    public User findByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);

    }


    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();

    }


    @Override
    public boolean checkUser(String userName, User user) {
        User userCheck = userRepository.findByUserName(userName);
        if (userCheck.getPassword().equals(user.getPassword())) {
            return true;
        }
        else {
            return false;
        }
    }


}