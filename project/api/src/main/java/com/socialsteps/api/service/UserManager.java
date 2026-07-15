package com.socialsteps.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.socialsteps.api.model.Notification;
import com.socialsteps.api.model.User;
import com.socialsteps.api.repo.NotificationRepository;
import com.socialsteps.api.repo.UserRepository;



@Service
public class UserManager {
    
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    public UserManager(UserRepository userRepository, NotificationRepository notificationRepository){
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }
    
    public User addUser(User user){
        user.setPassword(hash(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public User validateLogin(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        List<User> queriedUsers = getAllUsers();
        for (User u : queriedUsers){
            if (u.getUsername().equals(user.getUsername()) &&
            encoder.matches(user.getPassword(), u.getPassword())) {
            return u;
            }
        }
        return null;
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void sendInvite(Long senderId, String recipientUsername){
        User sender = userRepository.findById(senderId).orElseThrow();
        User recipient = userRepository.findByUsername(recipientUsername)
        .orElseThrow();

        Notification notification = new Notification(sender, recipient);
        notificationRepository.save(notification);
    }
    
    public String hash(String str){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(str);
    }

    @Transactional
    public List<User> getFriendsById(Long id){
        User user = getUserById(id);
        System.out.println("USER"+ user.getUsername());
        return user.getFriends();
        
    }

    @Transactional
    public List<Notification> getNotificationsById(Long id){
        User user = getUserById(id);
        return user.getNotifications();
    }
}
