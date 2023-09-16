package com.mystite.blog.controllers;

import com.mystite.blog.models.Post;

import com.mystite.blog.models.Subscription;
import com.mystite.blog.models.User;
import com.mystite.blog.repositories.PostRepository;

import com.mystite.blog.repositories.SubscriptionRepository;
import com.mystite.blog.services.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private LikeService likeService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/posts")
    public String posts(Model model){
        model.addAttribute("posts", postService.showAll());
        model.addAttribute("postLikesCount",  postService.getPostLikesCount(postService.showAll()));
        model.addAttribute("userSubsCount", postService.getSubscribersCount(postService.showAll()));
        model.addAttribute("usernames", postService.getUsernamesByPost(postService.showAll()));
        return "post/posts";
    }

    @GetMapping("/post/{postId}")
    public String details(@PathVariable(value = "postId") long postId, Model model){
        model.addAttribute("post", postService.showDetails(postId));
        model.addAttribute("user", userService.getAuthUser());
        model.addAttribute("isSubscriber", subscriptionService.isSubscriber(postId));
        model.addAttribute("isLiked", likeService.isLiked(postId));
        return "post/post_details";
    }

    @PostMapping("/post/{postId}/subscribe")
    public String subscribe(@PathVariable(value = "postId") long postId, Model model){
        subscriptionService.subscribe(postId);
        return "redirect:/posts";
    }
    @PostMapping("/post/{postId}/addComment")
    public String addComment(@PathVariable(value = "postId") long postId, @RequestParam String comment){
        commentService.addComment(postId, comment);
        return "redirect:/posts";
    }


    @PostMapping("/post/{postId}")
    public String likePost(@PathVariable(value = "postId") long postId){
        likeService.likePost(postId);
        return "redirect:/posts";
    }

    @GetMapping("/post/{postId}/edit")
    public String edit(@PathVariable(value = "postId") long postId, Model model){
        model.addAttribute("post", postService.showDetails(postId));
        return "post/post_edit";
    }

    @PostMapping("/post/{postId}/edit")
    public String update(@PathVariable(value = "postId") long postId, @RequestParam String text, @RequestParam int storyPrice, @RequestParam int feedPrice){
        postService.postEdit(text, storyPrice, feedPrice, postId);
        return "redirect:/posts";
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
