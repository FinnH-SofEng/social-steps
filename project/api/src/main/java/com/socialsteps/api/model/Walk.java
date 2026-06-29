package com.socialsteps.api.model;

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

    private Long creatorId;

    @ManyToMany
    private List<User> participants = new ArrayList<>();

    private String name;

    @OneToOne
    private Route route;
    private LocalDateTime time;

    public Walk(Long userId, String name, LocalDateTime time){
        this.creatorId = userId;
        this.name = name;
        this.time = time;
    }

    public Walk(String name, List<User> participants, Route route, LocalDateTime time) {
        this.name = name;
        this.participants = participants;
        this.route = route;
        this.time = time;
    }

    public Long getCreatorId(){
        return this.creatorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
    return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public void addParticipant(User participant){
        this.participants.add(participant);
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