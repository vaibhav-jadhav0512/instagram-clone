package com.authorization.server.repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.authorization.server.dto.UserUpdateRequest;
import com.authorization.server.exception.UserAlreadyExistsException;
import com.authorization.server.exception.UsernameNotFoundException;
import com.authorization.server.model.User;
import com.authorization.server.model.UserProfile;
import com.authorization.server.repository.rowmapper.UserProfileRowMapper;
import com.authorization.server.repository.rowmapper.UserRowMapper;
import com.authorization.server.repository.sql.AuthorizationQueries;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public Optional<User> findByUsername(String userName) {
		Map<String, Object> params = new HashMap<>();
		params.put("userName", userName);
		try {
			User user = template.queryForObject(AuthorizationQueries.FIND_BY_USERNAME, params, new UserRowMapper());
			return Optional.ofNullable(user);
		} catch (Exception e) {
			throw new UsernameNotFoundException("User not found by username: " + userName);
		}
	}

	@Override
	public void addUser(User user) {
		log.info("Adding user:{}", user.toString());
		Map<String, Object> params = new HashMap<>();
		params.put("userName", user.getUsername());
		params.put("fullname", user.getFullname());
		params.put("email", user.getEmail());
		params.put("password", user.getPassword());
		try {
			template.update(AuthorizationQueries.ADD_USER, params);
		} catch (Exception e) {
			if (e instanceof SQLIntegrityConstraintViolationException || e instanceof DuplicateKeyException)
				throw new UserAlreadyExistsException("User already exists");
		}
	}

	@Override
	public UserProfile getUserProfile(String userName) {
		log.info("Getting user profile:{}", userName);
		Map<String, Object> params = new HashMap<>();
		params.put("username", userName);
		try {
			return template.queryForObject(AuthorizationQueries.GET_USER_PROFILE, params, new UserProfileRowMapper());
		} catch (Exception e) {
			throw new RuntimeException("Username not found");
		}
	}

	@Override
	public int updateUserProfile(UserUpdateRequest userUpdateRequest) {
		StringBuilder updateQueryBuilder = new StringBuilder("UPDATE insta.`user` SET ");
		Map<String, Object> params = new HashMap<>();
		params.put("username", userUpdateRequest.getUsername());
		if (userUpdateRequest.getFullname() != null) {
			updateQueryBuilder.append("fullname = :fullName, ");
			params.put("fullName", userUpdateRequest.getFullname());
		}
		if (userUpdateRequest.getBio() != null) {
			updateQueryBuilder.append("bio = :bio, ");
			params.put("bio", userUpdateRequest.getBio());
		}
		if (updateQueryBuilder.charAt(updateQueryBuilder.length() - 2) == ',') {
			updateQueryBuilder.delete(updateQueryBuilder.length() - 2, updateQueryBuilder.length());
		}
		updateQueryBuilder.append(" WHERE user_name = :username");
		return template.update(updateQueryBuilder.toString(), params);
	}

}
