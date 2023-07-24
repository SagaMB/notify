package com.notify;


import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping("/{accountId}")
    public Notification saveNotificationWithUser(@PathVariable String accountId, @RequestBody Notification noti) {
        Notification notification = notificationService.saveNotification(noti);
        return notification;
    }
	
	@GetMapping("/m")
	public String getmessage() {
		return "Working...";
	}
}
