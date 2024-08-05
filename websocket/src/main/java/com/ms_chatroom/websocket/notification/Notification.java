package com.ms_chatroom.websocket.notification;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    private String id;
    private String senderId;
    private String hostId;
    private String content;
}
