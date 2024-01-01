package com.insta.activity.repo.sql;

public class FollowQueries {

	public static final String FOLLOW_USER = "INSERT INTO insta.follow " + "(follower_user_id, following_user_id) "
			+ "VALUES(:followerUserId,:followingUserId)";

}
