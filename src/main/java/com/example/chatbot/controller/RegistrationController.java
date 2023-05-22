package com.example.chatbot.controller;

import com.example.chatbot.business.concrete.NewsServiceImpl;
import com.example.chatbot.business.concrete.UserServiceImpl;

import com.example.chatbot.entity.News;
import com.example.chatbot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@Data
public class RegistrationController {
    public static String nameOfUser;
    private final UserServiceImpl userService;

    @GetMapping("/")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/")
    public String processLogin(@RequestParam(value = "userName", required = false) String username, @RequestParam(value = "password", required = false) String password, HttpSession httpSession) {
        User user = new User();
        nameOfUser=username;
        user.setUserName(username);
        user.setPassword(password);
        httpSession.setAttribute("commentsForUser",user.getComments());
        /*
        StringName.valUserName = username;*/

        if (user.getUserName().equals("admin") && user.getPassword().equals("12345")) {
            httpSession.setAttribute("isAdmin",true);
            httpSession.setAttribute("adminName",user.getUserName());
            return "redirect:/admin";
        }
        if (userService.checkUser(username, user)) {
            httpSession.setAttribute("username", username);
            httpSession.setAttribute("isAdmin",false);
            return "redirect:/mainPage/news";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(name = "/mainPage", method = RequestMethod.GET)
    public String loginHome(Model model, HttpSession httpSession) {
        String userName = (String) httpSession.getAttribute("username");
        model.addAttribute("userName", userName);
        return "indexes";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult) {
        User existing = userService.findByUsername(user.getUserName());
        if (existing != null) {
            bindingResult.reject("userName", null, "There is already an account registered");
        }

        userService.saveUser(user);
        return "redirect:/";
    }



/*

    @GetMapping("/admin")
    public String showAdminPage(Model model){
    }
*/

}
