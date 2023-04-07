package com.example.chatbot.controller;

import com.example.chatbot.business.concrete.UserServiceImpl;
import com.example.chatbot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;


@Controller
@AllArgsConstructor
@Data
@Transactional
public class UserController {

    private UserServiceImpl userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveRegisterPage(@ModelAttribute("user")User user) {
        bCryptPasswordEncoder.encode(user.getPassword());
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@RequestParam("username")String userName,@RequestParam("password")String password){
        User user=userService.findByUsername(userName);
        if(user !=null && user.getPassword().equals(password)){
            return "indexes";
        }
       /* else if(user !=null && user.getUserName().equals("admin") && user.getPassword().equals("12345")){
            return "admin_page";
        }
       */ else{
            return "login";
        }
    }
}
