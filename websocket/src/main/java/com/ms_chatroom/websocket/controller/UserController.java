package com.ms_chatroom.websocket.controller;

import com.ms_chatroom.websocket.service.UserService;
import com.ms_chatroom.websocket.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;


    @MessageMapping("/user.addUser")
    @SendTo("user/topic")
    public User addUser(@Payload User user){
        service.saveUser(user);
        return user;
    }


    @MessageMapping("/user.disconnectUser")
    @SendTo("user/topic")
    public User disconnect(@Payload User user){
        service.logoutUser(user);
        return user;
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers(){
        return ResponseEntity.ok(service.findConnectedUsers());
    }

}
