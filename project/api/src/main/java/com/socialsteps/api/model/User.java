package com.socialsteps.api.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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

    @ManyToMany
    @JoinTable(
        name = "users_friends",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    @JsonIgnore
    private Set<User> friends = new HashSet<>();

    public User(){
        this.notifications = new ArrayList<>();
    }
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.notifications = new ArrayList<>();
    }

    public Set<User> getFriends() {
    return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public User addFriend(User friend){
        this.notifications.add(new Notification(this, friend));
        //this.friends.add(friend);
        return friend;
    }

    public void acceptFriend(User friend) {
        friends.add(friend);
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

    public void addNotification(Notification notification){
        this.notifications.add(notification);
    }

}
