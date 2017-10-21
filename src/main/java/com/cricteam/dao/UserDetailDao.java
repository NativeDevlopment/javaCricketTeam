package com.cricteam.dao;

import com.cricteam.models.UserCompleteDetails;
import com.cricteam.models.UserDetails;

public interface UserDetailDao {
	UserDetails saveUser(UserDetails otp);
	UserDetails getUserViaUserId(int userId);
	UserCompleteDetails getUserViaMobileNo(String mobileNo);
	UserDetails updateUser(UserDetails userDetails, String userId);
	UserDetails updateUserDetails(UserDetails userDetails);
	UserCompleteDetails getUserCompleteDetailsViaUserId(int userId);
}
