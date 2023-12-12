package com.notification.be.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.notification.be.constant.NotificationConstants;

@Configuration
public class KafkaConfig {

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name(NotificationConstants.COMMENT_ADDED).build();
	}
}
