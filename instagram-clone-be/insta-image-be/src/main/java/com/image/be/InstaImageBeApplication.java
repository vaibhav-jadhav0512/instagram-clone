package com.image.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InstaImageBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstaImageBeApplication.class, args);
	}

}
