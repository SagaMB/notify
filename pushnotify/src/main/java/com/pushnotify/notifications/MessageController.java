package com.pushnotify.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noti")
public class MessageController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    
    @Autowired
    NotificationService notificationService;

    // Mapped as /app/application
    @MessageMapping("/application")
    @SendTo("/all/messages")
    public Message send(final Message message) throws Exception {
        return message;
    }

    // Mapped as /app/private
    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload Message message) {
        simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/specific", message);
    }
    
 // Mapped as /app/notifications/{accountId}
    @MessageMapping("/notifications/{accountId}")
    @SendToUser("/specific")
    public Notification sendNotification(@Payload Notification notification, @DestinationVariable String accountId) {
        notificationService.saveNotification(notification);
        simpMessagingTemplate.convertAndSendToUser(accountId, "/specific", notification);
        return notification;
    }
}