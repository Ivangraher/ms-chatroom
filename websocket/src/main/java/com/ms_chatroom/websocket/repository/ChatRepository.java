package com.ms_chatroom.websocket.repository;

import com.ms_chatroom.websocket.chatroom.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRepository extends MongoRepository<ChatRoom, String> {

    Optional<ChatRoom> findBySenderIdAndHostId(String senderId, String hostId);
}
