package com.authorization.server.service;

import java.util.Optional;

import com.authorization.server.dto.UserUpdateRequest;
import com.authorization.server.model.User;
import com.authorization.server.model.UserProfile;

public interface UserService {

	Optional<User> getUserDetails(String token);

	UserProfile getUserProfile(String username);

	int updateUserProfile(UserUpdateRequest userUpdateRequest);

}
