package com.actions.be.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.actions.be.model.FollowRequest;
import com.actions.be.repository.sql.FollowQueries;

@Repository
public class FollowRepositoryImpl implements FollowRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public int followUser(FollowRequest followRequest) {
		Map<String, Object> params = new HashMap<>();
		params.put("followerUserId", followRequest.getFollowerUserId());
		params.put("followingUserId", followRequest.getFollowingUserId());
		return template.update(FollowQueries.FOLLOW_USER, params);
	}

}
