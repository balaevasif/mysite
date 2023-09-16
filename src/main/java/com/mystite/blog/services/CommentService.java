package com.mystite.blog.services;

import com.mystite.blog.models.Comment;
import com.mystite.blog.models.Post;
import com.mystite.blog.models.User;
import com.mystite.blog.repositories.CommentRepository;
import com.mystite.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;

    public void addComment(long postId, String text){
        Post post = postRepository.findById(postId).orElseThrow();
        User user = userService.getAuthUser();
        Comment comment = new Comment(post, user, text);
        commentRepository.save(comment);
    }

//    public List<Comment> comments(){
//
//    }
}
