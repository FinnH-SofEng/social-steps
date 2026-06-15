package com.socialsteps.api.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.socialsteps.api.WalkRepository;
import com.socialsteps.api.model.Walk;

@Service
public class WalkManager {
    private final WalkRepository walkRepository;
    private List<Walk> walks;

    public WalkManager(WalkRepository walkRepository){
        this.walks = new ArrayList<>();
        this.walkRepository = walkRepository;
    }

    public Walk addWalk(String name, LocalDateTime time){
        Walk walk = new Walk(generateId(), name, time);
        
        this.walks.add(walk);
        walkRepository.save(walk);
        return walk;
    }

    private Long generateId(){
        Long max = 0L;
        for(Walk walk : walks){
            if(walk.getId() > max){
                max = walk.getId();
            }
        }
        return max + 1;
    }

    public List<Walk> getAllWalks() {
        return walkRepository.findAll();
    }
}
