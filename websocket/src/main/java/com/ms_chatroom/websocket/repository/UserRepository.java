package com.ms_chatroom.websocket.repository;

import com.ms_chatroom.websocket.user.Status;
import com.ms_chatroom.websocket.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAllByStatus(Status status);
}
