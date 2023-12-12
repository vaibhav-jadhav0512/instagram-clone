package com.comment.be.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	@KafkaListener(topics = "comment-added", groupId = "comment")
	public void notification(String message) {
		System.out.println(message);
	}
}
