package com.example.photographsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.photographsystem.models.Notification;
import com.example.photographsystem.models.User;
import com.example.photographsystem.repositories.NotificationRepository;
import com.example.photographsystem.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository,
                               UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAllByDeleteStatusFalse();
    }

    

    public Optional<Notification> getNotificationById(String id) {
        return notificationRepository.findByIdAndDeleteStatusFalse(id);
    }

    public Notification createNotification(Notification notification, String receiverId) {
        Optional<User> userOptional = userRepository.findById(receiverId);
        if (userOptional.isPresent()) {
            notification.setReceiver(userOptional.get());
            notification.setDeleteStatus(false);

            return notificationRepository.save(notification);
        }
        return null;
    }

    public boolean deleteNotification(String id) {
        return notificationRepository.findById(id).map(notification -> {
            notification.setDeleteStatus(true);
            notificationRepository.save(notification);
            return true;
        }).orElse(false);
    }

    public void createUserNotification(String userId, String title, String subtitle) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setSubtitle(subtitle);
        createNotification(notification, userId);
    }
}