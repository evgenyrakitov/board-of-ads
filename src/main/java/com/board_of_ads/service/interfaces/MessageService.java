package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.Message;

import java.util.List;

public interface MessageService {
    long getCountUnreadMessagesForUserId(Long id);
    List<Message> getAllMessage();
    Message addMessage(Message message);
     Message getMessageByID(long id);
}
