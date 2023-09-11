package com.mystite.blog.controllers;

import com.mystite.blog.models.User;
import com.mystite.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/personalAccount/signIn")
    public String signIn(){
        return "signIn";
    }

    @GetMapping("/personalAccount/signUp")
    public String signUp(){
        return "signUp";
    }

    @PostMapping("/personalAccount/signUp")
    public String register(@RequestParam String username, @RequestParam String password){
        User user = new User(username, password);
        userRepository.save(user);
        return "redirect:/personalAccount/signIn";
    }
}
