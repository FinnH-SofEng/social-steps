package com.socialsteps.api.command;

import java.time.LocalDateTime;

import com.socialsteps.api.model.Walk;
import com.socialsteps.api.service.WalkManager;


public class CreateWalk implements Action<Walk>{
    private WalkManager receiver;
    private String name;
    private LocalDateTime time;
    private Long creatorId;
    public CreateWalk(WalkManager receiver, Long creatorId, String name, LocalDateTime time){
        this.receiver = receiver;
        this.name = name;
        this.time = time;
        this.creatorId = creatorId;
    }
    @Override
    public Walk performAction() {
        return receiver.addWalk(creatorId, name, time);
    }
    
}
