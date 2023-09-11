package com.mystite.blog.models;

import jakarta.persistence.*;

import java.util.Date;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    public Post() {
    }

    public Post(String text, int storyPrice, int feedPrice, Date published, byte[] image) {
        this.text = text;
        this.storyPrice = storyPrice;
        this.feedPrice = feedPrice;
        this.published = published;
        this.image = image;
    }

    public Long getId() {
        return postId;
    }

    public void setId(Long postId) {
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
}
