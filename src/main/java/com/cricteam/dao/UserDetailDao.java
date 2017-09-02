package com.cricteam.dao;

import com.cricteam.models.UserDetails;

public interface UserDetailDao {
	UserDetails saveUser(UserDetails otp);
	UserDetails getUserViaUserId(String userId);
	UserDetails getUserViaMobileNo(String mobileNo);
	UserDetails updateUser(UserDetails userDetails, String userId);
	UserDetails updateUserDetails(UserDetails userDetails);
}
