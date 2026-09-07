package com.socialsteps.api.model;

import jakarta.persistence.*;

@Entity
public class GoogleCalendarConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private String refreshToken;

    public GoogleCalendarConnection() {}

    public GoogleCalendarConnection(User user, String refreshToken) {
        this.user = user;
        this.refreshToken = refreshToken;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}