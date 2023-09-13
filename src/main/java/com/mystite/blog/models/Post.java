package com.mystite.blog.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;
    private String text;
    private int storyPrice;
    private int feedPrice;
    private Date published;
    private int views;
    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;
    @Transient
    private String imageBase64;
    public Post() {
    }
    public Post(String text, int storyPrice, int feedPrice, byte[] image, User user) {
        this.text = text;
        this.storyPrice = storyPrice;
        this.feedPrice = feedPrice;
        this.published = new Date();
        this.image = image;
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    public String getImageBase64() {
        return imageBase64;
    }

    public Long postId() {
        return postId;
    }

    public void postId(Long postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStoryPrice() {
        return storyPrice;
    }

    public void setStoryPrice(int storyPrice) {
        this.storyPrice = storyPrice;
    }

    public int getFeedPrice() {
        return feedPrice;
    }

    public void setFeedPrice(int feedPrice) {
        this.feedPrice = feedPrice;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
