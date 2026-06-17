package com.socialsteps.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialsteps.api.model.Walk;

public interface WalkRepository extends JpaRepository<Walk, Long> {}