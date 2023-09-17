package com.mystite.blog.services;

import com.mystite.blog.models.Like;
import com.mystite.blog.models.Post;
import com.mystite.blog.models.User;
import com.mystite.blog.repositories.LikeRepository;
import com.mystite.blog.repositories.PostRepository;
import com.mystite.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public void registerUser(String username, String password){
        String hashedPassword = passwordEncoder.encode(password);
        userRepository.save(new User(username, hashedPassword));
    }
    //Получить текущего авторизованного пользователя
    public User getAuthUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName());
    }

    public void updateInfo(String mail, MultipartFile image) throws IOException {
        User user = getAuthUser();
        byte[] imageData = image.getBytes();
        user.setImage(imageData);
        user.setMail(mail);
        userRepository.save(user);
    }
    public void updateInfo(String mail){
        User user = getAuthUser();
        user.setMail(mail);
        userRepository.save(user);
    }

    public void updateInfo(MultipartFile image) throws IOException {
        User user = getAuthUser();
        byte[] imageData = image.getBytes();
        user.setImage(imageData);
        userRepository.save(user);
    }
}
