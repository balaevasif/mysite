package com.mystite.blog.controllers;

import com.mystite.blog.models.Post;
import com.mystite.blog.models.User;
import com.mystite.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;


    @GetMapping("/posts")
    public String posts(){
        return "post/posts";
    }
    @GetMapping("/posts/add")
    public String Add(){
        return "post/post_add";
    }
    @PostMapping("/posts/add")
    public String postAdd(@RequestParam String postText, @RequestParam int storyPrice, @RequestParam int feedPrice, Date date, @RequestParam("image") MultipartFile image, User user) throws IOException {
        byte[] imageData = image.getBytes();
        Post post = new Post(postText, storyPrice, feedPrice, date, imageData, user);
        postRepository.save(post);
        return "redirect:/posts";

    }

}
