//package com.mystite.blog.models;
//
//import jakarta.persistence.*;
//
//import java.util.Date;
//
//@Entity
//@Table(name = "Subscriptions")
//public class Subscription {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "subscriber_id")
//    private User subscriber;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "subscribed_to_id")
//    private User subscribedTo;
//
//    private Date dateSubscribed;
//
//    public Subscription() {
//    }
//
//    public Subscription(User subscriber, User subscribedTo) {
//        this.subscriber = subscriber;
//        this.subscribedTo = subscribedTo;
//        this.dateSubscribed = new Date();
//    }
//
//
//}
