package com.socialsteps.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/walks")
public class WalkController {

    private final WalkRepository walkRepository;

    public WalkController(WalkRepository walkRepository) {
        this.walkRepository = walkRepository;
    }

    @PostMapping
    public Walk createWalk(@RequestBody Walk walk) {
        return walkRepository.save(walk);
    }

    @GetMapping
    public List<Walk> getAllWalks() {
        return walkRepository.findAll();
    }
}