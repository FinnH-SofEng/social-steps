package com.socialsteps.api;

import org.springframework.data.jpa.repository.JpaRepository;

//Basic SQL commands
public interface WalkRepository extends JpaRepository<Walk, Long> {
}