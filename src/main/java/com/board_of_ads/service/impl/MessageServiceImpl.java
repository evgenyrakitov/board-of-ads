package com.board_of_ads.service.impl;

import com.board_of_ads.models.Message;
import com.board_of_ads.repository.MessageRepository;
import com.board_of_ads.service.interfaces.MessageService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    private final MessageRepository messageRepository;

    @Override
    public long getCountUnreadMessagesForUserId(Long id){
        return messageRepository.countMessagesByReadStatusAndRecipientId(Message.ReadStatus.NOT_READ, id);
    }

    @Override
    public List<Message> getAllMessage() {
        return messageRepository.findAll();
    }

    @Override
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message getMessageByID(long id) {
        return messageRepository.findById(id).orElse(null);
    }
}