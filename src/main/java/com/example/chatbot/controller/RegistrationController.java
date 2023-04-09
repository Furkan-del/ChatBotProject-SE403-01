package com.example.chatbot.controller;

import com.example.chatbot.business.concrete.UserServiceImpl;

import com.example.chatbot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Data
public class RegistrationController {
    private UserServiceImpl userService;

    @GetMapping("/")
    public String showLoginForm(){
        return "login";
    }

     @PostMapping("/")
     public String processLogin(@RequestParam(value = "username",required = false)String username,@RequestParam(value = "password",required = false)String password){
        User user=new User();
        user.setUserName(username);
        user.setPassword(password);
         if(userService.checkUser(username,user)){
             return "redirect:/mainPage";
         }
         else if (user.getUserName().equals("admin")&&user.getPassword().equals("12345")) {
             return  "redirect:/admin";
         } else{
             return "redirect:/";
         }
     }

     @RequestMapping(name = "/mainPage",method = RequestMethod.GET)
     public String loginHome(){
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
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            bindingResult.reject("email", null, "There is already an account registered");
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
