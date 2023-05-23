package com.example.chatbot.business.concrete;

import com.example.chatbot.business.abstracts.UserService;
import com.example.chatbot.dataAccesLayer.UserRepository;
import com.example.chatbot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Data
public class UserServiceImpl implements UserService  {
    private final UserRepository userRepository;

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



  /*  @Override
    public boolean checkUser(String userName, User user) {
        User userCheck = userRepository.findByUserName(userName);
        if (userCheck.getPassword().equals(user.getPassword())) {
            return true;
        }
        else {
            return false;
        }
    }*/
}

