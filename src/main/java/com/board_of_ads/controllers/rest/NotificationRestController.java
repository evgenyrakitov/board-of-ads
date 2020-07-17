package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.Notification;
import com.board_of_ads.service.interfaces.NotificationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/notification")
@AllArgsConstructor
public class NotificationRestController {

    private static final Logger logger = LoggerFactory.getLogger(RoleRestController.class);

    private final NotificationService notificationService;

    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotification() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/getNotificationInfo")
    public ResponseEntity<Notification> getNotification() {
        Notification notification = new Notification();
        notification.setTitle("Тестовая нотификация");
        notification.setContent("Я проснулся. Или нет? WHAT IS LIFE? A FRENZY. WHAT IS LIFE? AN ILLUSION. A SHADOW OF A " +
                "FICTION.  ALL LIFE IS A DREAM.");
        notification.setType(Notification.Type.News);
        notification.setRead(false);
        return ResponseEntity.ok(notification);
    }
}
