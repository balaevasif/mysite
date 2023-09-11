package com.mystite.blog.models;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    private String username;
    private String password;

    private String mail;

    private Date registerDate;

    private Date birthday;



    public Long getId() {
        return user_id;
    }

    public void setId(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User() {
    }
}
