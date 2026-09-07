package com.socialsteps.api.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialsteps.api.model.GoogleCalendarConnection;
import com.socialsteps.api.model.User;

@Repository
public interface GoogleCalendarConnectionRepository
        extends JpaRepository<GoogleCalendarConnection, Long> {

    Optional<GoogleCalendarConnection> findByUser(User user);
}