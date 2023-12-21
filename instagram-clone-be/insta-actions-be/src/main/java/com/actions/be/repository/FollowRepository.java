package com.actions.be.repository;

import com.actions.be.model.FollowRequest;

public interface FollowRepository {

	int followUser(FollowRequest followRequest);

}
