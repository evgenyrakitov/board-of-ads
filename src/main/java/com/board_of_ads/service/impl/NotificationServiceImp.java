package com.board_of_ads.service.impl;

import com.board_of_ads.models.Notification;
import com.board_of_ads.repository.NotificationRepository;
import com.board_of_ads.service.interfaces.NotificationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class NotificationServiceImp implements NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    private final NotificationRepository notificationRepository;

    @Override
    public void addNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public void deleteNotificationById(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public void setStatusNotification(Long id, boolean value) {
        Notification notification = notificationRepository.findNotificationById(id);
        notification.setRead(value);
        notificationRepository.save(notification);
    }

    @Override
    public Notification findNotificationById(Long id) {
        return notificationRepository.findNotificationById(id);
    }

    @Override
    public List<Notification> getAllNotifications() {
       return notificationRepository.findAll();
    }
}
