package com.comment.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InstaCommentBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstaCommentBeApplication.class, args);
	}

}
