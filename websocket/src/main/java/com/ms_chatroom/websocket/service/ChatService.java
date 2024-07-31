package com.ms_chatroom.websocket.service;

import com.ms_chatroom.websocket.chatroom.ChatRoom;
import com.ms_chatroom.websocket.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository repository;

    public Optional<String> getChatId(String senderId, String hostId, boolean createNewRoomIfNotExists) {

        return repository.findBySenderIdAndHostId(senderId, hostId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(createNewRoomIfNotExists){
                        var chatId = createChatId(senderId, hostId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }


    private String createChatId(String senderId, String hostId){
        var chatId = String.format("%s_%s", senderId, hostId);

        ChatRoom roomHost = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .hostId(hostId)
                .build();

        repository.save(roomHost);

        ChatRoom roomSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .hostId(hostId)
                .build();

        repository.save(roomSender);

        return chatId;
    }
}
