package com.mystite.blog.services;

import com.mystite.blog.models.User;
import com.mystite.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public User registerUser(String username, String password){
        String hashedPassword = passwordEncoder.encode(password);
        return userRepository.save(new User(username, hashedPassword));
    }
}
