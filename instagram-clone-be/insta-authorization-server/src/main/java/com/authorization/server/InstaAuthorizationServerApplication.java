package com.authorization.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = { "com.authorization.server" })
@EnableDiscoveryClient
public class InstaAuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstaAuthorizationServerApplication.class, args);
	}

}
