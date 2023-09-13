package com.mystite.blog.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Subscribers")
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subId;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    private List<User> subscribers = new ArrayList<>();


}
