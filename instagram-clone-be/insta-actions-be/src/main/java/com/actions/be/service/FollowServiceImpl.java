package com.actions.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actions.be.model.FollowRequest;
import com.actions.be.repository.FollowRepository;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowRepository followRepository;

	@Override
	public int followUser(FollowRequest followRequest) {
		return followRepository.followUser(followRequest);
	}

}
