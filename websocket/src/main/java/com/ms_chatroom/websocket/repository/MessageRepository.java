package com.ms_chatroom.websocket.repository;

import com.ms_chatroom.websocket.message.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findByChatId(String s);
}
