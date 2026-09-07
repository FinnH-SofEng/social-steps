package com.socialsteps.api.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;
import com.socialsteps.api.model.GoogleCalendarConnection;
import com.socialsteps.api.model.User;
import com.socialsteps.api.model.Walk;
import com.socialsteps.api.repo.UserRepository;
import com.socialsteps.api.repo.GoogleCalendarConnectionRepository;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class GoogleCalendarManager {
    private final UserRepository userRepository;
    private final GoogleCalendarConnectionRepository googleCalendarConnectionRepository;

    @Value("${google.client-id}")
    private String clientId;

    @Value("${google.client-secret}")
    private String clientSecret;

    @Value("${google.redirect-uri}")
    private String redirectUri;
    
    public GoogleCalendarManager(UserRepository userRepository, GoogleCalendarConnectionRepository googleCalendarConnectionRepository){
        this.userRepository = userRepository;
        this.googleCalendarConnectionRepository = googleCalendarConnectionRepository;
    }

    public String getAuthorizationUrl(Long userId) {
        // Build Google's OAuth authorization URL
        return "goy";
    }

    public void handleCallback(String code, Long userId) {
        // Exchange authorization code for tokens
        // Save user's Google credentials
    }

    public void addWalkToCalendar(Long userId, Walk walk) {
        // Get credentials
        // Convert Walk -> Google Event
        // Insert event
    }

    public GoogleCalendarConnection getConnectionByUser(User user){
        return googleCalendarConnectionRepository.findByUser(user).orElseThrow();
    }
    public void saveConnection(GoogleCalendarConnection connection){
        googleCalendarConnectionRepository.save(connection);
    }
    public Event addWalkToCalendar(User user, Walk walk) throws Exception {

    Calendar calendar = buildCalendarClient(user);

    Event event = new Event()
        .setSummary(walk.getName())
        .setDescription("Social Steps Walk");

    // Example: 1-hour walk
    LocalDateTime startTime = walk.getTime();
    LocalDateTime endTime = startTime.plusHours(1);

    ZoneId zone = ZoneId.systemDefault();

    ZonedDateTime startZoned = startTime.atZone(zone);
    ZonedDateTime endZoned = endTime.atZone(zone);

    EventDateTime start = new EventDateTime()
        .setDateTime(
            new DateTime(startZoned.toInstant().toEpochMilli())
        );

    EventDateTime end = new EventDateTime()
        .setDateTime(
            new DateTime(endZoned.toInstant().toEpochMilli())
        );

    event.setStart(start);
    event.setEnd(end);

    if (walk.getLocation() != null) {
        event.setLocation(walk.getLocation().getName());
    }

    return calendar.events()
        .insert("primary", event)
        .execute();
}

private Calendar buildCalendarClient(User user) throws Exception {

    GoogleCalendarConnection connection =
        googleCalendarConnectionRepository
            .findByUser(user)
            .orElseThrow();

    GoogleCredential credential = new GoogleCredential.Builder()
        .setTransport(GoogleNetHttpTransport.newTrustedTransport())
        .setJsonFactory(GsonFactory.getDefaultInstance())
        .setClientSecrets(clientId, clientSecret)
        .build();

    credential.setRefreshToken(connection.getRefreshToken());
    credential.refreshToken();

    return new Calendar.Builder(
        GoogleNetHttpTransport.newTrustedTransport(),
        GsonFactory.getDefaultInstance(),
        credential
    )
    .setApplicationName("SocialSteps")
    .build();
}
}
