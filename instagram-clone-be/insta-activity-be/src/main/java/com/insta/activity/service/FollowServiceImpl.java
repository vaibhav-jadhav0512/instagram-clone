package com.insta.activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.activity.model.FollowRequest;
import com.insta.activity.repo.FollowRepository;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowRepository followRepository;

	@Override
	public int followUser(FollowRequest followRequest) {
		return followRepository.followUser(followRequest);
	}

}
