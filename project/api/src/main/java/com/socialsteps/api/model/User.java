package com.socialsteps.api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isLoggedIn;
    private String username;

   
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "recipient")
    private List<Notification> notifications;

    @JsonIgnore
    @ManyToMany
    private List<User> friends;

    public User(){
        this.notifications = new ArrayList<>();
        this.friends = new ArrayList<>();
    }
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.notifications = new ArrayList<>();
        this.friends = new ArrayList<>();
    }

    public List<User> getFriends(){
        return this.friends;
    }

    public void setFriends(List<User> friends){
        this.friends = friends;    
    }

    public User addFriend(User friend){
        this.friends.add(friend);
        return friend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Boolean getIsloggedIn(){
        return this.isLoggedIn;
    }

    public void logIn(){
        this.isLoggedIn = true;
    }

    public void logOut(){
        this.isLoggedIn = false;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public List<Notification> getNotifications(){
        return this.notifications;
    }

    public void setNotifications(List<Notification> notifications){
        this.notifications = notifications;
    }

}
