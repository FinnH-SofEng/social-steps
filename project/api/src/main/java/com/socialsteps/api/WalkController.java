package com.socialsteps.api;

import org.springframework.web.bind.annotation.*;
import java.util.List;

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