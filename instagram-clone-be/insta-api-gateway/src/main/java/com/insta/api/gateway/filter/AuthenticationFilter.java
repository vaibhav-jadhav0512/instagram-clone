package com.insta.api.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	@Autowired
	private RouteValidator routeValidator;

	private final String AUTH_URL = "http://localhost:8888/api/v1/auth/validate?token=";

	private final RestTemplate restTemplate = new RestTemplate();

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if (routeValidator.isSecured.test(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
					throw new RuntimeException("Missing Authorization header");
				String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeaders != null && authHeaders.startsWith("Bearer ")) {
					authHeaders = authHeaders.substring(7);
				}
				HttpHeaders headers = new HttpHeaders();
				headers.setBearerAuth(authHeaders);
				HttpEntity<Void> entity = new HttpEntity<>(headers);
				try {
					ResponseEntity<Void> authResponse = restTemplate.exchange(AUTH_URL + authHeaders, HttpMethod.GET,
							entity, Void.class);
					if (authResponse.getStatusCode().is2xxSuccessful()) {
						log.info("Token valideted at API gateway");
					}
				} catch (HttpClientErrorException e) {
					log.error("Token validation failed:{}", e.getMessage());
				}
			}
			return chain.filter(exchange);
		});
	}

//test
	public static class Config {
	}
}
