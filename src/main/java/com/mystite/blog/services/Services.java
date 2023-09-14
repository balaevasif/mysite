package com.mystite.blog.services;

import com.mystite.blog.repositories.LikeRepository;
import com.mystite.blog.repositories.PostRepository;
import com.mystite.blog.repositories.SubscriptionRepository;
import com.mystite.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Services {
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected PostRepository postRepository;
    @Autowired
    protected LikeRepository likeRepository;
    @Autowired
    protected SubscriptionRepository subscriptionRepository;
}
