package com.mystite.blog.controllers;

import com.mystite.blog.models.User;
import com.mystite.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

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
        return "authentication/signIn";
    }

    @GetMapping("/personalAccount/signUp")
    public String signUp(){
        return "authentication/signUp";
    }

    @PostMapping("/personalAccount/signUp")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String password_2, Model model){
        User userFromDB = userRepository.findByUsername(username);
        String specialCharsPattern = ".*[!@#$%^&*()_\\-+=<>?/].*";

        if (userFromDB != null){
            logger.info("User with username {} already exists", username);
            model.addAttribute("message", "Данный пользователь уже существует");
            return "authentication/signUp";
        }
        else {
            if (username.length() < 7){
                model.addAttribute("message", "Слишком короткое имя пользователя");
                return "authentication/signUp";
            }
            else if (password.length() < 7){
                model.addAttribute("message", "Слишком короткий пароль");
                return "authentication/signUp";
            }
            else if (!password.matches(specialCharsPattern)) {
                model.addAttribute("message", "Пароль должен содержать хотя бы один специальный символ (!@#$%^&*()_-+=<>?/)");
                return "authentication/signUp";
            }
            else if (!Objects.equals(password, password_2)){
                logger.info("Password are not same");
                model.addAttribute("message", "Пароли не совпадают");
                return "authentication/signUp";
            }
            else {
                String hashedPassword = passwordEncoder.encode(password);
                User user = new User(username, hashedPassword);
                userRepository.save(user);
                logger.info("User with username: {} saved successfully", username);
                return "redirect:/personalAccount/signIn";
            }
        }
    }
}
