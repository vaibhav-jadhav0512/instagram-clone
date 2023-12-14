package com.authorization.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authorization.server.config.JwtService;
import com.authorization.server.dto.AuthRequest;
import com.authorization.server.dto.AuthResponse;
import com.authorization.server.dto.RegisterRequest;
import com.authorization.server.exception.IncorrectPasswordException;
import com.authorization.server.exception.JwtExpiredException;
import com.authorization.server.exception.JwtParseException;
import com.authorization.server.exception.UsernameNotFoundException;
import com.authorization.server.model.Role;
import com.authorization.server.model.User;
import com.authorization.server.model.UserProfile;
import com.authorization.server.repository.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;
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
		var user = User.builder().fullName(regRequest.getFullname()).userName(regRequest.getUsername())
				.email(regRequest.getEmail()).password(passwordEncoder.encode(regRequest.getPassword())).role(Role.USER)
				.build();
		repository.addUser(user);
		log.info("User registered:{}", user.toString());
		var jwtToken = jwtService.generateToken(user);
		return AuthResponse.builder().token(jwtToken).build();
	}

	public AuthResponse authenticate(AuthRequest authRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (org.springframework.security.core.userdetails.UsernameNotFoundException e) {
			throw new UsernameNotFoundException("User not found");
		} catch (BadCredentialsException e) {
			throw new IncorrectPasswordException("Incorrect password");
		}
		var user = repository.findByUsername(authRequest.getUsername()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthResponse.builder().token(jwtToken).build();
	}

	public boolean validateToken(String token) {
		try {
			String userName = jwtService.extractUserName(token);
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
			return jwtService.isTokenValid(token, userDetails);
		} catch (ExpiredJwtException e) {
			throw new JwtExpiredException("Token expired");
		} catch (Exception e) {
			throw new JwtParseException("Jwt token tampered");
		}
	}

	public Optional<User> getUserDetails(String token) {
		try {
			String userName = jwtService.extractUserName(token);
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
			if (jwtService.isTokenValid(token, userDetails)) {
				return repository.findByUsername(userName);
			} else {
				return null;
			}
		} catch (ExpiredJwtException e) {
			throw new JwtExpiredException("Token expired");
		} catch (Exception e) {
			throw new JwtParseException("Jwt token tampered");
		}
	}

	public UserProfile getUserProfile(String username) {
		UserProfile userProfile = repository.getUserProfile(username);
		if (userProfile.getUserName() == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return userProfile;
	}

}
