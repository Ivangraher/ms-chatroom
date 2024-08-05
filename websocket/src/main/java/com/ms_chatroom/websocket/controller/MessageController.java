package com.ms_chatroom.websocket.controller;


import com.ms_chatroom.websocket.message.Message;
import com.ms_chatroom.websocket.notification.Notification;
import com.ms_chatroom.websocket.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final SimpMessagingTemplate template;

    @MessageMapping
    public void processMessage(@Payload Message message){
        Message savedMsg = messageService.save(message);
        template.convertAndSendToUser(savedMsg.getHostId(), "/queue/messages", Notification.builder()
                .id(savedMsg.getId())
                .senderId(savedMsg.getSenderId())
                .hostId(savedMsg.getHostId())
                .content(savedMsg.getContent())
                .build());

    }



    @GetMapping("/messages/{senderId}/{hostId}")
    public ResponseEntity<List<Message>> findMessages(@PathVariable("senderId") String senderId,
                                                      @PathVariable("hostId") String hostId){
        return ResponseEntity.ok(messageService.findMessages(senderId, hostId));
    }
}
