package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.Notification;
import com.board_of_ads.models.User;

import java.util.List;
import java.util.Set;

public interface NotificationService {
    void addNotification(Notification notification);
    void deleteNotificationById(Long id);
    void setStatusNotification(Long id, boolean value);
    Notification findNotificationById(Long id);
    List<Notification> getAllNotifications();
    List<Notification> getAllPostByUserId(Long id);
    //List<Notification> getGroupNotification(Set<Long> manyId);   //суем кучу id, возвращаем лист.
//    List<Notification>
}
