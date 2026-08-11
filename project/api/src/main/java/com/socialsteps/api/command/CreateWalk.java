package com.socialsteps.api.command;

import java.time.LocalDateTime;

import com.socialsteps.api.model.Walk;
import com.socialsteps.api.service.WalkManager;


public class CreateWalk implements Action<Walk>{
    private WalkManager receiver;
    private String name;
    private LocalDateTime time;
    private Long creatorId;
    private Double latitude;
    private Double longitude;
    public CreateWalk(WalkManager receiver, Long creatorId, String name, LocalDateTime time, Double latitude, Double longitude){
        this.receiver = receiver;
        this.name = name;
        this.time = time;
        this.creatorId = creatorId;
        this.latitude = latitude;
        this.longitude = longitude;

    }
    @Override
    public Walk performAction() {
        return receiver.addWalk(creatorId, name, time, latitude, longitude);
    }
    
}
