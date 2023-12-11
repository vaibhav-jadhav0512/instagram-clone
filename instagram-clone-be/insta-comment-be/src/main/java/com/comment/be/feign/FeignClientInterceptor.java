package com.comment.be.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignClientInterceptor {
	@Bean
	public RequestInterceptor requestInterceptor() {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate requestTemplate) {
				ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
						.getRequestAttributes();
				if (attributes != null) {
					String jwtToken = attributes.getRequest().getHeader("Authorization");
					if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
						requestTemplate.header("Authorization", jwtToken);
					}
				}
			}
		};
	}
}