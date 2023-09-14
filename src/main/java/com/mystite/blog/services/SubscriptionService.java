package com.mystite.blog.services;

import com.mystite.blog.controllers.AuthController;
import com.mystite.blog.models.Subscription;
import com.mystite.blog.models.User;
import com.mystite.blog.repositories.PostRepository;
import com.mystite.blog.repositories.SubscriptionRepository;
import com.mystite.blog.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public void subscribe(long postId){
        Post post = postRepository.findById(postId).orElseThrow();
        User user = post.getUser();
        //User user = subscriptionRepository.findUserByPostId(postId);
        User subscriber = userService.getAuthUser();
        Subscription subscription = subscriptionRepository.findBySubscriberAndUser(subscriber, user);
        if (subscription != null){
            subscriptionRepository.delete(subscription);
        }
        else {
            subscription = new Subscription(subscriber, user);
            subscriptionRepository.save(subscription);
        }
    }

    public boolean isSubscribed(long postId){
        Post post = postRepository.findById(postId);
        User user = post.getUser();
        User subscriber = userService.getAuthUser();
        


}
