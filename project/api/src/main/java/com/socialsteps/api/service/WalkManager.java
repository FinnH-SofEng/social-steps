package com.socialsteps.api.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.socialsteps.api.model.User;
import com.socialsteps.api.model.Walk;
import com.socialsteps.api.repo.UserRepository;
import com.socialsteps.api.repo.WalkRepository;

@Service
public class WalkManager {
    private final WalkRepository walkRepository;
    private final UserRepository userRepository;
    private List<Walk> walks;

    public WalkManager(WalkRepository walkRepository, UserRepository userRepository){
        this.walkRepository = walkRepository;
        this.userRepository = userRepository;
        
        this.walks = this.getAllWalks();
    }

    public Walk addWalk(Long creatorId, String name, LocalDateTime time){
        Walk walk = new Walk(creatorId, name, time);
        
        List<User> users = userRepository.findAll();
        for(User user : users){
            if(user.getId() == creatorId){
                walk.addParticipant(user);
            }
        }
        this.walks.add(walk);
        walkRepository.save(walk);
        return walk;
    }

    public List<Walk> getAllWalks() {
        return walkRepository.findAll();
    }

    public List<Walk> getWalksById(Long id){
        System.out.println("THIS FUNCTION GOT CALLED");
        List<Walk> allWalks = walkRepository.findAll();
        List<Walk> userWalks = new ArrayList<>();
        for(Walk walk : allWalks){
            for(User user : walk.getParticipants()){
                System.out.println("" + user.getId() + " " + id);
                if(user.getId() == id){
                    System.out.println("ADDED");
                    userWalks.add(walk);
                }
            }
        }
        return userWalks;
    }
}
