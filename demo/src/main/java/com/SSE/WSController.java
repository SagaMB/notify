package com.SSE;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.SSE.dto.Message;
import com.SSE.dto.ResponseMessage;

@RestController
public class WSController {

    @Autowired
    private WSService service;
    
    @Autowired
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WSController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable final String id,
                                   @RequestBody final Message message) {
        service.notifyUser(id, message.getMessageContent());
    }
    
    @MessageMapping("/private-message")
    @SendToUser("/specific/private-messages")
    public ResponseMessage getPrivateMessage(final Message message,
                                             final Principal principal) throws InterruptedException {
        messagingTemplate.convertAndSendToUser(principal.getName(),"/specific/private-notifications", message);
        return new ResponseMessage(HtmlUtils.htmlEscape(
                "Sending private message to user " + principal.getName() + ": "
                        + message.getMessageContent())
        );
    }
}
