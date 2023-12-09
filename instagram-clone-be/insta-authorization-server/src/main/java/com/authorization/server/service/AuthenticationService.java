package com.authorization.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authorization.server.config.JwtService;
import com.authorization.server.dto.AuthRequest;
import com.authorization.server.dto.AuthResponse;
import com.authorization.server.dto.RegisterRequest;
import com.authorization.server.model.Role;
import com.authorization.server.model.User;
import com.authorization.server.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;

	public AuthResponse register(RegisterRequest regRequest) {
		var user = User.builder().userName(regRequest.getUsername()).email(regRequest.getEmail())
				.password(passwordEncoder.encode(regRequest.getPassword())).role(Role.USER).build();
		repository.addUser(user);
		log.info("User registered:{}", user.toString());
		var jwtToken = jwtService.generateToken(user);
		return AuthResponse.builder().token(jwtToken).build();
	}

	public AuthResponse authenticate(AuthRequest authRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		var user = repository.findByUsername(authRequest.getUsername()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthResponse.builder().token(jwtToken).build();
	}

	public boolean validateToken(String token) {
		String userName = jwtService.extractUserName(token);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
		return jwtService.isTokenValid(token, userDetails);
	}

}
