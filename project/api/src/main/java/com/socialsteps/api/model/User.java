package com.socialsteps.api.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @OneToMany(mappedBy = "recipient")
    private List<Notification> notifications;

    public User(){

    }
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
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
