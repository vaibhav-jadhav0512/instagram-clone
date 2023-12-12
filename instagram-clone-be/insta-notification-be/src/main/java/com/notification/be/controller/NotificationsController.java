package com.notification.be.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.be.service.NotificationsService;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {

	@Autowired
	private NotificationsService notificationsService;

	@PostMapping("/publish")
	public ResponseEntity<Map<String, Object>> publish() throws InterruptedException {
		int range = 100;
		while (range > 0) {
			notificationsService.publish("Vaibhav commented on your photo");
			Thread.sleep(1000);
			range--;
		}
		return new ResponseEntity<>(Map.of("message", "Someone commented"), HttpStatus.OK);
	}

}
