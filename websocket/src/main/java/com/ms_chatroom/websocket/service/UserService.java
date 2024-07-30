package com.ms_chatroom.websocket.service;

import com.ms_chatroom.websocket.repository.UserRepository;
import com.ms_chatroom.websocket.user.Status;
import com.ms_chatroom.websocket.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user){
        user.setStatus(Status.ONLINE);
        userRepository.save(user);

    }

    public void logoutUser(User user){
        var storedUser = userRepository.findById(user.getNickName())
                .orElse(null);
        if(storedUser != null){
            user.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers(){

        return userRepository.findAllByStatus(Status.ONLINE);
    }


}
