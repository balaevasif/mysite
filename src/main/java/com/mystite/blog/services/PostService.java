package com.mystite.blog.services;

import com.mystite.blog.models.Post;
import com.mystite.blog.models.User;
import com.mystite.blog.repositories.PostRepository;
import com.mystite.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public Post addPost(String postText, int storyPrice, int feedPrice, MultipartFile image) throws IOException {
        byte[] imageData = image.getBytes();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        return postRepository.save(new Post(postText, storyPrice, feedPrice, imageData, user));
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
        post.ifPresent(result::add);
        return result;
    }
}