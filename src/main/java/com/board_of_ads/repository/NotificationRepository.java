package com.board_of_ads.repository;

import com.board_of_ads.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Notification findNotificationById(Long id);
}
