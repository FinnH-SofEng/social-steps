package com.socialsteps.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.socialsteps.api.command.Action;
import com.socialsteps.api.command.CreateWalk;
import com.socialsteps.api.model.Walk;
import com.socialsteps.api.service.WalkManager;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/walks")
public class WalkController extends Controller<Walk>{

    private final WalkManager walkManager;
    private Action<Walk> command;
   
    public WalkController(WalkManager walkManager) {
        this.walkManager = walkManager;
    }

    @PostMapping
    public Walk createWalk(@RequestBody Walk walk) {
        this.command = new CreateWalk(this.walkManager, walk.getName(), walk.getTime());

        return command.performAction();
    }

    @GetMapping
    public List<Walk> getAllWalks() {
        return walkManager.getAllWalks();
    }

    
}