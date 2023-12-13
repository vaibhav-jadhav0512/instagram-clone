package com.authorization.server.repository.sql;

public class AuthorizationQueries {

	public static final String FIND_BY_USERNAME = "SELECT user_id, user_name, email, password, role, created_at,fullname "
			+ "FROM insta.`user` WHERE user_name=:userName";
	public static final String ADD_USER = "INSERT INTO insta.`user` " + "(user_name, email, password,fullname) "
			+ "VALUES(:userName,:email,:password,:fullname)";

}
