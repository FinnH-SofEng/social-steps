package com.socialsteps.api.controller;

import java.io.IOException;

import org.checkerframework.checker.units.qual.g;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.socialsteps.api.model.User;
import com.socialsteps.api.model.Walk;
import com.socialsteps.api.model.GoogleCalendarConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.socialsteps.api.service.UserManager;
import com.socialsteps.api.service.WalkManager;
import com.socialsteps.api.service.GoogleCalendarManager;

import com.socialsteps.api.repo.GoogleCalendarConnectionRepository;
import com.socialsteps.api.repo.UserRepository;
@RestController
@RequestMapping("/api/google")
public class GoogleCalendarController {
    private final UserManager userManager;
    private final WalkManager walkManager;
    private final GoogleCalendarManager googleCalendarManager;

    private final UserRepository userRepository;
    private final GoogleCalendarConnectionRepository googleCalendarConnectionRepository;

    @Value("${google.client-id}")
    private String clientId;

    @Value("${google.client-secret}")
    private String clientSecret;

    @Value("${google.redirect-uri}")
    private String redirectUri;

    public GoogleCalendarController(UserManager userManager, WalkManager walkManager, GoogleCalendarManager googleCalendarManager, UserRepository userRepository, GoogleCalendarConnectionRepository googleCalendarConnectionRepository){
        this.userManager = userManager;
        this.walkManager = walkManager;
        this.googleCalendarManager = googleCalendarManager;
        this.userRepository = userRepository;
        this.googleCalendarConnectionRepository = googleCalendarConnectionRepository;
    }

    @GetMapping("/connect")
        public void connectGoogle(
                @RequestParam Long userId,
                HttpServletResponse response
        ) throws IOException {

            String authorizationUrl =
                new GoogleAuthorizationCodeRequestUrl(
                    clientId,
                    redirectUri,
                    List.of("https://www.googleapis.com/auth/calendar.events")
                )
                .setAccessType("offline")
                .set("prompt", "consent")
                .setState(userId.toString())
                .build();

            response.sendRedirect(authorizationUrl);
    }
    @PostMapping("/calendar/walk/{walkId}")
    public Event addWalkToCalendar(
            @PathVariable Long walkId,
            @RequestParam Long userId) throws Exception {

        User user = userManager.getUserById(userId);

        Walk walk = walkManager.getWalkById(walkId);

        return googleCalendarManager.addWalkToCalendar(user, walk);
    }

@GetMapping("/callback")
public String handleGoogleCallback(
        @RequestParam String code,
        @RequestParam String state) {

    try {
        GoogleTokenResponse tokenResponse =
            new GoogleAuthorizationCodeTokenRequest(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                clientId,
                clientSecret,
                code,
                redirectUri
            ).execute();

        Long userId = Long.parseLong(state);

        User user = userRepository.findById(userId)
            .orElseThrow(() ->
                new RuntimeException(
                    "No Social Steps user found with id " + userId
                )
            );

        String refreshToken = tokenResponse.getRefreshToken();

        if (refreshToken == null) {
            return "No refresh token received from Google";
        }

        GoogleCalendarConnection connection =
            googleCalendarConnectionRepository
                .findByUser(user)
                .orElse(new GoogleCalendarConnection());

        connection.setUser(user);
        connection.setRefreshToken(refreshToken);

        googleCalendarConnectionRepository.save(connection);

        return "Google Calendar connected successfully!";

    } catch (Exception e) {
        e.printStackTrace();
        return "Google Calendar connection failed: "
            + e.getClass().getSimpleName()
            + ": "
            + e.getMessage();
    }
}
}