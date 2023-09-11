package com.mystite.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String empty(){
        return "index";
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
    @GetMapping("/personalAccount")
    public String personalAccount(){
        return "personalAccount";
    }
}
