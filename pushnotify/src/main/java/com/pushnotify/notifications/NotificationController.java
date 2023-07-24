package com.pushnotify.notifications;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noti")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

//	@PostMapping("/{accountId}")
//    public Notification saveNotificationWithUser(@PathVariable String accountId, @RequestBody Notification noti) {
//        Notification notification = notificationService.saveNotification(noti);
//
//        // Send the notification to the user via WebSocket
//        Message message = new Message();
//        message.setText("You have a new notification: " + notification.getName());
//        messagingTemplate.convertAndSendToUser(accountId, "/specific", message);
//
//        return notification;
//    }
	
	
	@PostMapping("/n")
    public Notification saveNotificationWithUser(@RequestBody Notification noti) {

        return noti;
    }
	
	@GetMapping("/m")
	public String getmessage() {
		return "Working...";
	}
}
