package com.mystite.blog.services;

import com.mystite.blog.models.Post;
import com.mystite.blog.models.User;
import com.mystite.blog.repositories.PostRepository;
import com.mystite.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public void addPost(String postText, int storyPrice, int feedPrice, MultipartFile image) throws IOException {
        byte[] imageData = image.getBytes();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        postRepository.save(new Post(postText, storyPrice, feedPrice, imageData, user));
    }

    public List<Post> showAll(){
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            String imageBase64 = Base64.getEncoder().encodeToString(post.getImage());
            post.setImageBase64(imageBase64);
        }
        return posts;
    }

    public ArrayList<Post> showDetails(long postId){
        Optional<Post> post = postRepository.findById(postId);
        ArrayList<Post> result = new ArrayList<>();
        post.ifPresent(p -> {
            String imageBase64 = Base64.getEncoder().encodeToString(p.getImage());
            p.setImageBase64(imageBase64);
            result.add(p);
            p.setViews(p.getViews()+1);
            postRepository.save(p);
        });
        return result;
    }

    public void postEdit(String text, int storyPrice, int feedPrice, long postId){
        Post post = postRepository.findById(postId).orElseThrow();
        post.setText(text);
        post.setStoryPrice(storyPrice);
        post.setFeedPrice(feedPrice);
        postRepository.save(post);
    }
}
