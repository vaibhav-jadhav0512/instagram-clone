package com.authorization.server.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.authorization.server.dto.UserUpdateRequest;
import com.authorization.server.model.User;
import com.authorization.server.model.UserProfile;
import com.authorization.server.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/get/user-details")
	public ResponseEntity<Optional<User>> getUserDetails(@RequestParam("token") String token) {
		return new ResponseEntity<>(userService.getUserDetails(token), HttpStatus.OK);
	}

	@GetMapping("/get/profile/{username}")
	public ResponseEntity<UserProfile> getUserProfile(@PathVariable("username") String username) {
		return new ResponseEntity<>(userService.getUserProfile(username), HttpStatus.OK);
	}

	@PutMapping("/profile/update")
	public ResponseEntity<Integer> updateUserProfile(@RequestBody UserUpdateRequest userUpdateRequest) {
		return new ResponseEntity<>(userService.updateUserProfile(userUpdateRequest), HttpStatus.OK);
	}
}
