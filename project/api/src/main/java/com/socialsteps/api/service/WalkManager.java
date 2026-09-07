package com.socialsteps.api.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.socialsteps.api.model.Location;
import com.socialsteps.api.model.Notification;
import com.socialsteps.api.model.User;
import com.socialsteps.api.model.Walk;
import com.socialsteps.api.repo.LocationRepository;
import com.socialsteps.api.repo.NotificationRepository;
import com.socialsteps.api.repo.UserRepository;
import com.socialsteps.api.repo.WalkRepository;

@Service
public class WalkManager {
    private final WalkRepository walkRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final LocationRepository locationRepository;
    

    public WalkManager(WalkRepository walkRepository, UserRepository userRepository, NotificationRepository notificationRepository, LocationRepository locationRepository){
        this.walkRepository = walkRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.locationRepository = locationRepository;
        
        
    }

    public Walk addWalk(
        Long creatorId,
        String name,
        LocalDateTime time,
        Double latitude,
        Double longitude) {

    User creator = userRepository.findById(creatorId)
        .orElseThrow();

    Location location = new Location(latitude, longitude);
    location = locationRepository.save(location);

    Walk walk = new Walk(creatorId, name, time);
    walk.setLocation(location);
    walk.addParticipant(creator);

    return walkRepository.save(walk);
}

    public void inviteUser(Long senderId, Long walkId, Long userId){
        User sender = userRepository.findById(senderId).orElseThrow();
        Walk walk = walkRepository.findById(walkId).orElseThrow();
        User recipient = userRepository.findById(userId).orElseThrow();

        Notification notification = new Notification(sender, walk, recipient);

        notificationRepository.save(notification);
    }

    public void acceptInvite(Long notificationId){
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();

        notification.getWalk().addParticipant(notification.getRecipient());

        notificationRepository.delete(notification);
    }

    public void declineInvite(Long notificationId){
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();

        notificationRepository.delete(notification);
    }

    public Walk getWalkById(Long id){
        return walkRepository.findById(id).orElseThrow();
    }


    public List<Walk> getAllWalks() {
        return walkRepository.findAll();
    }

    public List<Walk> getWalksById(Long id){
        System.out.println("THIS FUNCTION GOT CALLED");
        List<Walk> allWalks = walkRepository.findAll();
        List<Walk> userWalks = new ArrayList<>();
        for(Walk walk : allWalks){
            for(User user : walk.getParticipants()){
                System.out.println("" + user.getId() + " " + id);
                if(user.getId().equals(id)){
                    System.out.println("ADDED");
                    userWalks.add(walk);
                }
            }
        }
        return userWalks;
    }
}
