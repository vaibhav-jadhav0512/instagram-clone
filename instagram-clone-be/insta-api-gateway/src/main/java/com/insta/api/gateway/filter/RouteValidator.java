package com.insta.api.gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@Component
public class RouteValidator {

	public static final List<String> openApiEndpoints = List.of("/api/v1/auth/**", "/eureka");

	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
			.noneMatch(uri -> pathMatcher.match(uri, request.getURI().getPath()));
}
