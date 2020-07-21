package com.board_of_ads.repository;

import com.board_of_ads.models.Notification;
import com.board_of_ads.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Notification findNotificationById(Long id);
    List<Notification> findNotificationByUser_Id(Long id);
}
