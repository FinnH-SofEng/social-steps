package com.socialsteps.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialsteps.api.model.Walk;

//Basic SQL commands
public interface WalkRepository extends JpaRepository<Walk, Long> {
}