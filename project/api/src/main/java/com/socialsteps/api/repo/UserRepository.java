package com.socialsteps.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialsteps.api.model.User;

public interface UserRepository extends JpaRepository<User, Long>{}
