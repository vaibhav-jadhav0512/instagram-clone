package com.authorization.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

	private String fullName;
	private String userName;
	private String email;
	private int posts;
	private int followers;
	private int following;
	private String bio;
}
