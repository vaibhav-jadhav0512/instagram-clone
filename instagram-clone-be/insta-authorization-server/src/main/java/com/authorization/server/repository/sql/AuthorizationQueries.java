package com.authorization.server.repository.sql;

public class AuthorizationQueries {

	public static final String FIND_BY_USERNAME = "SELECT user_id, user_name, email, password, role, created_at,fullname,bio "
			+ "FROM insta.`user` WHERE user_name=:userName";
	public static final String ADD_USER = "INSERT INTO insta.`user` " + "(user_name, email, password,fullname) "
			+ "VALUES(:userName,:email,:password,:fullname)";
	public static final String GET_USER_PROFILE = "SELECT  u.user_name,  u.fullname,  u.email, u.bio,  COUNT(DISTINCT p.post_id) AS posts, "
			+ "COUNT(DISTINCT f1.follow_id) AS followers,  COUNT(DISTINCT f2.follow_id) AS following "
			+ "FROM user u LEFT JOIN post p ON u.user_id = p.user_id LEFT JOIN "
			+ "follow f1 ON u.user_id = f1.following_user_id LEFT JOIN "
			+ "follow f2 ON u.user_id = f2.follower_user_id WHERE u.user_name = :username";

}
