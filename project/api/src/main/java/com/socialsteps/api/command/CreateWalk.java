package com.socialsteps.api.command;

import java.time.LocalDateTime;

import com.socialsteps.api.model.Walk;
import com.socialsteps.api.service.WalkManager;


public class CreateWalk implements Action<Walk>{
    private WalkManager receiver;
    private String name;
    private LocalDateTime time;
    public CreateWalk(WalkManager receiver, String name, LocalDateTime time){
        this.receiver = receiver;
        this.name = name;
        this.time = time;

    }
    @Override
    public Walk performAction() {
        return receiver.addWalk(name, time);
    }
    
}
