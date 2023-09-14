package com.mystite.blog.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subUserId")
    private User subscriber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    private Date dateSubscribed;

    public Subscription() {
    }

    public Subscription(User subscriber, User user) {
        this.subscriber = subscriber;
        this.user = user;
        this.dateSubscribed = new Date();
    }


}
