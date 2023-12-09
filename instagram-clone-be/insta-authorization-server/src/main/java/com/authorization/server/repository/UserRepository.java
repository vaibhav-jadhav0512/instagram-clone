package com.authorization.server.repository;

import java.util.Optional;

import com.authorization.server.model.User;

public interface UserRepository {

	Optional<User> findByUsername(String userName);

	void addUser(User user);
}
