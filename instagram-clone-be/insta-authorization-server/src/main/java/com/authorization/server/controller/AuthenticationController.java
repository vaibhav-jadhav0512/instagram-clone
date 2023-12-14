package com.authorization.server.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.authorization.server.dto.AuthRequest;
import com.authorization.server.dto.AuthResponse;
import com.authorization.server.dto.RegisterRequest;
import com.authorization.server.model.User;
import com.authorization.server.model.UserProfile;
import com.authorization.server.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService service;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest regRequest) {
		return new ResponseEntity<>(service.register(regRequest), HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
			return new ResponseEntity<>(service.authenticate(authRequest), HttpStatus.OK);
	}

	@GetMapping("/validate")
	public ResponseEntity<Boolean> validateToken(@RequestParam("token") String token) {
		return new ResponseEntity<>(service.validateToken(token), HttpStatus.OK);
	}

	@GetMapping("/user")
	public ResponseEntity<Optional<User>> getUserDetails(@RequestParam("token") String token) {
		return new ResponseEntity<>(service.getUserDetails(token), HttpStatus.OK);
	}

	@GetMapping("/user/profile/{username}")
	public ResponseEntity<UserProfile> getUserProfile(@PathVariable("username") String username) {
		return new ResponseEntity<>(service.getUserProfile(username), HttpStatus.OK);
	}
}
