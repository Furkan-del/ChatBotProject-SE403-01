package com.example.chatbot.business.concrete;

import com.example.chatbot.business.abstracts.UserService;
import com.example.chatbot.dataAccesLayer.UserRepository;
import com.example.chatbot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Data
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    public User findByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    //check için
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    //check için

    //kullanıcıyı kaydetmek için

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return user;
    }
}
