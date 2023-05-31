package com.example.chatbot.controller;

import com.example.chatbot.business.abstracts.UserService;
import com.example.chatbot.business.concrete.CustomUserDetailsService;
import com.example.chatbot.business.concrete.NewsServiceImpl;
import com.example.chatbot.business.concrete.UserServiceImpl;

import com.example.chatbot.entity.News;
import com.example.chatbot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@Data
public class RegistrationController {
    public static String nameOfUser;
    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/")
    public String showLoginForm() {
        return "login";
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

    @PostMapping("/")
    public String processLogin(@RequestParam(value = "userName", required = false) String username, @RequestParam(value = "password", required = false) String password, HttpSession httpSession) {

       try {
           UserDetails userDetails=customUserDetailsService.loadUserByUsername(username);
           Authentication authentication=new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
           SecurityContextHolder.getContext().setAuthentication(authentication);

           User user = new User();
           nameOfUser=username;
           user.setUserName(username);
           user.setPassword(password);
           httpSession.setAttribute("commentsForUser",user.getComments());
        /*
        StringName.valUserName = username;*/

           if (username.equals("admin")&& user.getPassword().equals("12345")) {
               httpSession.setAttribute("isAdmin",true);
               httpSession.setAttribute("adminName",username);
               return "redirect:/admin";
           }
           else{
               httpSession.setAttribute("username", username);
               httpSession.setAttribute("isAdmin",false);
               return "redirect:/mainPage/news";
           }
       }catch (UsernameNotFoundException exception){
           return "redirect:/";
       }

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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

/*

    @GetMapping("/admin")
    public String showAdminPage(Model model){
    }
*/

}
