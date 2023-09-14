package com.mystite.blog.services;

import com.mystite.blog.models.Like;
import com.mystite.blog.models.Post;
import com.mystite.blog.models.User;
import com.mystite.blog.repositories.LikeRepository;
import com.mystite.blog.repositories.PostRepository;
import com.mystite.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserService userService;

    public void likePost(long postId){
        Post post = postRepository.findById(postId).orElseThrow();
        User user = userService.getAuthUser();
        Like like = likeRepository.findByPostAndUser(post, user);
        if (like != null){
            likeRepository.delete(like);
        }
        else {
            like = new Like(post, user);
            likeRepository.save(like);
        }
    }
}
