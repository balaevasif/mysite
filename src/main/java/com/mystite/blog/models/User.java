package com.mystite.blog.models;

import jakarta.persistence.*;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "mail"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String username;
    private String password;

    private String mail;

    private final Date registerDate = new Date();

    private Date birthday;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    @Transient
    private String imageBase64;


    public void setMail(String mail){
        this.mail = mail;
    }

    public String getMail(){
        return mail;
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
    public String getImageBase64() {
        return imageBase64;
    }

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Post> posts = new ArrayList<>();

    public Date getBirthday(){
        return birthday;
    }
    public void setBirthday(Date date){
        this.birthday = birthday;
    }

    public void setRegisterDate(Date date){
        this.birthday = birthday;
    }
    public Date getRegisterDate(){
        return registerDate;
    }

    public Long getId() {
        return userId;
    }


    public void setId(Long userId) {
        this.userId = userId;
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
        this.image = getDefaultImage();
    }
    public User() {
    }
    @Override
    public String toString() {
        return getUsername();
    }


    public static byte[] getDefaultImage() {
        try {
            InputStream in = User.class.getResourceAsStream("/static/pictures/emptyHeart.png");
            return IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

}
