package com.socialsteps.api.service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

import com.socialsteps.api.model.Walk;
import com.socialsteps.api.repo.WalkRepository;

@Service
public class WalkManager {
    private final WalkRepository walkRepository;
    private List<Walk> walks;

    public WalkManager(WalkRepository walkRepository){
        this.walkRepository = walkRepository;
        this.walks = this.getAllWalks();
    }

    public Walk addWalk(String name, LocalDateTime time){
        Walk walk = new Walk(name, time);
        
        this.walks.add(walk);
        walkRepository.save(walk);
        return walk;
    }

    public List<Walk> getAllWalks() {
        return walkRepository.findAll();
    }
}
