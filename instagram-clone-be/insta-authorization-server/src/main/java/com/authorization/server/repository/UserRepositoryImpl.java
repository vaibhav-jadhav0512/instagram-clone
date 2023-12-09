package com.authorization.server.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.authorization.server.model.User;
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
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public void addUser(User user) {
		log.info("Adding user:{}", user.toString());
		Map<String, Object> params = new HashMap<>();
		params.put("userName", user.getUsername());
		params.put("email", user.getEmail());
		params.put("password", user.getPassword());
		template.update(AuthorizationQueries.ADD_USER, params);

	}

}
