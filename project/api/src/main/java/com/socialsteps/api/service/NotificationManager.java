package com.socialsteps.api.service;

import org.springframework.stereotype.Service;

import com.socialsteps.api.model.Notification;
import com.socialsteps.api.model.NotificationType;
import com.socialsteps.api.model.User;
import com.socialsteps.api.repo.NotificationRepository;
import com.socialsteps.api.repo.UserRepository;

@Service
public class NotificationManager {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationManager(NotificationRepository notificationRepository,
                               UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public Notification createNotification(Long recipientId, NotificationType type, String message) {
        User recipient = userRepository.findById(recipientId).orElseThrow();

        Notification notification = new Notification();
        notification.setRecipient(recipient);
        notification.setType(type);
        notification.setMessage(message);

        return notificationRepository.save(notification);
    }
}
