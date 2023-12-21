package com.actions.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actions.be.model.FollowRequest;
import com.actions.be.service.FollowService;

@RestController
@RequestMapping("/api/v1/follow")
public class FollowController {

	@Autowired
	private FollowService followService;

	@PostMapping("/request")
	public ResponseEntity<Integer> followUser(@RequestBody FollowRequest followRequest) {
		return new ResponseEntity<>(followService.followUser(followRequest), HttpStatus.CREATED);
	}
}
