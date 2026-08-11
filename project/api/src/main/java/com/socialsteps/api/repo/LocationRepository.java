package com.socialsteps.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialsteps.api.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{

    
} 
