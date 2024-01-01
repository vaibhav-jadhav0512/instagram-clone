package com.insta.activity.repo;

import com.insta.activity.model.FollowRequest;

public interface FollowRepository {
	int followUser(FollowRequest followRequest);
}
