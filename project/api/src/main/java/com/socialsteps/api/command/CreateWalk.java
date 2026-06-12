package com.socialsteps.api.command;

import java.time.LocalDateTime;

import com.socialsteps.api.WalkManager;


public class CreateWalk implements Action{
    private WalkManager receiver;
    private String name;
    private LocalDateTime time;
    public CreateWalk(String name, LocalDateTime time){
        this.name = name;
        this.time = time;

    }
    @Override
    public void performAction() {
        receiver.addWalk(name, time);
    }
    
}
