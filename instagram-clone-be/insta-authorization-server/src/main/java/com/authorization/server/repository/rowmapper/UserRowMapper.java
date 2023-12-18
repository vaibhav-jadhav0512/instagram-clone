package com.authorization.server.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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
		return user;
	}

}
