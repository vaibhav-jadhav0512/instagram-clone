package com.insta.api.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.insta.api.gateway.config.JwtService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	@Autowired
	private RouteValidator routeValidator;

	@Autowired
	private JwtService jwtService;

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if(routeValidator.isSecured.test(exchange.getRequest())) {
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
					throw new RuntimeException("Missing Authorization header");
				String authHeaders=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if(authHeaders!=null && authHeaders.startsWith("Bearer ")) {
					authHeaders=authHeaders.substring(7);
				}
				try {
					jwtService.validateToken(authHeaders);
					log.info("Token validated");
				} catch (Exception e) {
					log.error("Invalid token");
					throw new RuntimeException("Invalid token");
				}
			}
			return chain.filter(exchange);
		});
	}

//test
	public static class Config {
	}
}
