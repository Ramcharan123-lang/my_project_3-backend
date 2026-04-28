package com.studentproject.backend.socket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.studentproject.backend.domain.Message;
import com.studentproject.backend.domain.User;
import com.studentproject.backend.repository.MessageRepository;
import com.studentproject.backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class SocketIOService {

    @Autowired
    private SocketIOServer server;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void startServer() {
        server.addListeners(this);
        server.start();
        System.out.println("Socket.IO Server started on port " + server.getConfiguration().getPort());
    }

    @PreDestroy
    public void stopServer() {
        server.stop();
        System.out.println("Socket.IO Server stopped");
    }

    @OnConnect
    public void onConnect(com.corundumstudio.socketio.SocketIOClient client) {
        System.out.println("User connected: " + client.getSessionId().toString());
    }

    @OnDisconnect
    public void onDisconnect(com.corundumstudio.socketio.SocketIOClient client) {
        System.out.println("User disconnected: " + client.getSessionId().toString());
    }

    @OnEvent("join_group")
    public void onJoinGroup(com.corundumstudio.socketio.SocketIOClient client, String groupId) {
        client.joinRoom(groupId);
        System.out.println("Socket " + client.getSessionId().toString() + " joined group " + groupId);
    }

    @OnEvent("send_message")
    public void onSendMessage(com.corundumstudio.socketio.SocketIOClient client, Map<String, String> data) {
        String senderId = data.get("sender");
        String groupId = data.get("groupId");
        String content = data.get("content");

        try {
            Message message = new Message();
            message.setSenderId(senderId);
            message.setGroupId(groupId);
            message.setContent(content);

            Message savedMessage = messageRepository.save(message);

            Optional<User> senderOpt = userRepository.findById(senderId);

            Map<String, Object> response = new HashMap<>();
            response.put("id", savedMessage.getId());
            response.put("content", savedMessage.getContent());
            response.put("groupId", savedMessage.getGroupId());
            response.put("createdAt", savedMessage.getCreatedAt().toString());
            
            if(senderOpt.isPresent()) {
                User sender = senderOpt.get();
                Map<String, Object> senderObj = new HashMap<>();
                senderObj.put("id", sender.getId());
                senderObj.put("_id", sender.getId());
                senderObj.put("name", sender.getName());
                response.put("sender", senderObj);
            } else {
                response.put("sender", senderId);
            }

            server.getRoomOperations(groupId).sendEvent("receive_message", response);

        } catch (Exception e) {
            System.err.println("Error saving message: " + e.getMessage());
        }
    }
}
