package com.project.feedbank.controller;

import com.project.feedbank.model.Feedback;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Feedback sendMessage(@Payload Feedback Feedback) {
        return Feedback;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Feedback addUser(@Payload Feedback Feedback, 
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", Feedback.getSender());
        return Feedback;
    }

}