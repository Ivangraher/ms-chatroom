package com.ms_chatroom.websocket.message;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Message {

    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String hostId;
    private String content;
    private Date timestamp;

}