package com.authorization.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

	private int userId;
	private String fullname;
	private String username;
	private String email;
	private int posts;
	private int followers;
	private int following;
	private String bio;
}
