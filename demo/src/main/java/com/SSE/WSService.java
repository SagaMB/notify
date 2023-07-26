package com.SSE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.SSE.dto.ResponseMessage;

@Service
public class WSService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WSService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyUser(final String id, final String message) {
        ResponseMessage response = new ResponseMessage(message);
        messagingTemplate.convertAndSendToUser(id, "/specific/private-messages", response);
    }
}

