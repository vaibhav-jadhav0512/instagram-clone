package com.notification.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.notification.be.constant.NotificationConstants;

@Service
public class NotificationsServiceImpl implements NotificationsService {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public boolean publish(String message) {
		kafkaTemplate.send(NotificationConstants.COMMENT_ADDED, message);
		return true;
	}

}
