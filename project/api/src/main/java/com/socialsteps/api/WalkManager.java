package com.socialsteps.api;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class WalkManager {
    private ArrayList<Walk> walks;

    public WalkManager(){
        this.walks = new ArrayList<>();
    }

    public void addWalk(String name, LocalDateTime time){
        this.walks.add(new Walk());
    }
}
