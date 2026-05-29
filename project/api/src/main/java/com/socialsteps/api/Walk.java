package com.socialsteps.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Walk {

    public Walk() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<User> participants = new ArrayList<>();

    @ManyToOne
    private Route route;

    private LocalDateTime time;

    public Walk(List<User> participants, Route route, LocalDateTime time) {
        this.participants = participants;
        this.route = route;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}