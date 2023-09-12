package com.mystite.blog.controllers;

import com.mystite.blog.models.Post;
import com.mystite.blog.models.User;
import com.mystite.blog.repositories.PostRepository;
import com.mystite.blog.repositories.UserRepository;
import com.mystite.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public String posts(Model model){
        model.addAttribute("posts", postService.showAll());
        return "post/posts";
    }


    @GetMapping("/post/{postId}")
    public String details(@PathVariable(value = "postId") long postId, Model model){
        model.addAttribute("post", postService.showDetails(postId));
        return "post/post_details";
    }





    @GetMapping("/posts/add")
    public String Add(){
        return "post/post_add";
    }
    @PostMapping("/posts/add")
    public String postAdd(@RequestParam String postText, @RequestParam int storyPrice, @RequestParam int feedPrice, @RequestParam("image") MultipartFile image) throws IOException {
        postService.addPost(postText, storyPrice, feedPrice, image);
        return "redirect:/posts";
    }

}