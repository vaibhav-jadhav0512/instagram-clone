package com.image.be.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

	private final String AUTH_URL = "http://localhost:8888/api/v1/auth/validate?token=";
	private final RestTemplate restTemplate = new RestTemplate();
	private final List<String> openEndpointPatterns = Arrays.asList("/api/v1/image/get/**");
	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestURI = request.getRequestURI();
		if (isOpenEndpoint(requestURI)) {
			filterChain.doFilter(request, response);
			return;
		}
		final String authHeader = request.getHeader("Authorization");
		final String jwtToken;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			response.setStatus(403);
			return;
		}
		jwtToken = authHeader.substring(7);
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(jwtToken);
		HttpEntity<Void> entity = new HttpEntity<>(headers);
		try {
			ResponseEntity<Void> authResponse = restTemplate.exchange(AUTH_URL + jwtToken, HttpMethod.GET, entity,
					Void.class);
			if (authResponse.getStatusCode().is2xxSuccessful()) {
				filterChain.doFilter(request, response);
			}
		} catch (HttpClientErrorException e) {
			response.setStatus(e.getRawStatusCode());
			response.getOutputStream().write(e.getResponseBodyAsByteArray());
			response.flushBuffer();
		}
	}

	private boolean isOpenEndpoint(String requestURI) {
		return openEndpointPatterns.stream().anyMatch(pattern -> pathMatcher.match(pattern, requestURI));
	}
}
