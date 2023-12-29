package com.authorization.server.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.RowMapper;

import com.authorization.server.model.Role;
import com.authorization.server.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("user_id"));
		user.setUsername(rs.getString("user_name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setRole(Role.valueOf(rs.getString("role")));
		user.setFullname(rs.getString("fullname"));
		user.setBio(rs.getString("bio"));
		String followersStr = rs.getString("followers");
		if (followersStr != null) {
			List<Integer> followers = parseCsvToIntList(followersStr);
			user.setFollowers(followers);
		}
		String followingStr = rs.getString("following");
		if (followingStr != null) {
			List<Integer> following = parseCsvToIntList(followingStr);
			user.setFollowing(following);
		}
		return user;
	}

	private List<Integer> parseCsvToIntList(String csv) {
		return Arrays.stream(csv.split(",")).map(Integer::parseInt).collect(Collectors.toList());
	}
}
