package com.authorization.server.repository;

import java.util.Optional;

import com.authorization.server.model.User;
import com.authorization.server.model.UserProfile;

public interface UserRepository {

	Optional<User> findByUsername(String userName);

	void addUser(User user);

	UserProfile getUserProfile(String userName);
}
