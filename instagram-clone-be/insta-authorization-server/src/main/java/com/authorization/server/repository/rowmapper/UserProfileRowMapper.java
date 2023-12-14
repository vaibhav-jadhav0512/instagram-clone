package com.authorization.server.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.authorization.server.model.UserProfile;

public class UserProfileRowMapper implements RowMapper<UserProfile> {

	@Override
	public UserProfile mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserProfile userProfile = new UserProfile();
		userProfile.setUserName(rs.getString("user_name"));
		userProfile.setFullName(rs.getString("fullname"));
		userProfile.setEmail(rs.getString("email"));
		userProfile.setBio(rs.getString("bio"));
		userProfile.setPosts(rs.getInt("posts"));
		userProfile.setFollowers(rs.getInt("followers"));
		userProfile.setFollowing(rs.getInt("following"));
		return userProfile;
	}
}
