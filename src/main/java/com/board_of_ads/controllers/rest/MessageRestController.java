package com.board_of_ads.controllers.rest;

import com.avito.service.interfaces.MessageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/messages")
@RequiredArgsConstructor
public class MessageRestController {

    private static final Logger logger = LoggerFactory.getLogger(MessageRestController.class);

    private final MessageService messageService;

    @GetMapping("/unreadCount/{id}")
    public ResponseEntity<Long> getCountUnreadMessages(@PathVariable("id") Long id) {
        Long countUnreadMessages = messageService.getCountUnreadMessagesForUserId(id);
        return ResponseEntity.ok(countUnreadMessages);
    }

}
