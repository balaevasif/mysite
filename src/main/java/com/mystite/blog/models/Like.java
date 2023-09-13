package com.mystite.blog.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Likes", uniqueConstraints = @UniqueConstraint(columnNames = {"postId", "userId"}))
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private Date date;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
        this.date = new Date();
    }

    public Like() {
    }
}
