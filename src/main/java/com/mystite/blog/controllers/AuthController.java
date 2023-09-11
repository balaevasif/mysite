package com.mystite.blog.controllers;

import com.mystite.blog.models.User;
import com.mystite.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/personalAccount/signIn")
    public String signIn(){
        //logger.info("Method signIn was called");
        return "signIn";
    }

    @GetMapping("/personalAccount/signUp")
    public String signUp(){
        return "signUp";
    }

    @PostMapping("/personalAccount/signUp")
    public String register(@RequestParam String username, @RequestParam String password){
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(username, hashedPassword);
        userRepository.save(user);
        logger.info("User with username: {} saved successfully", username);
        return "redirect:/personalAccount/signIn";
    }
}
