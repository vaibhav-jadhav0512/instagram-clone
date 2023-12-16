package com.authorization.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.authorization.server.config.JwtService;
import com.authorization.server.dto.UserUpdateRequest;
import com.authorization.server.exception.JwtExpiredException;
import com.authorization.server.exception.JwtParseException;
import com.authorization.server.exception.UsernameNotFoundException;
import com.authorization.server.model.User;
import com.authorization.server.model.UserProfile;
import com.authorization.server.repository.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public UserProfile getUserProfile(String username) {
		UserProfile userProfile = repository.getUserProfile(username);
		if (userProfile.getUserName() == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return userProfile;
	}

	@Override
	public int updateUserProfile(UserUpdateRequest userUpdateRequest) {
		int updateUserProfile = repository.updateUserProfile(userUpdateRequest);
		if (updateUserProfile > 0)
			return updateUserProfile;
		else {
			throw new UsernameNotFoundException("Username not found");
		}
	}

	@Override
	public Optional<User> getUserDetails(String token) {
		try {
			String userName = jwtService.extractUserName(token);
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
			if (jwtService.isTokenValid(token, userDetails)) {
				return repository.findByUsername(userName);
			} else {
				return null;
			}
		} catch (ExpiredJwtException e) {
			throw new JwtExpiredException("Token expired");
		} catch (Exception e) {
			throw new JwtParseException("Jwt token tampered");
		}
	}
}
