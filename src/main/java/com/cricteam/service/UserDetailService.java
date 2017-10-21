package com.cricteam.service;

import com.cricteam.models.UserCompleteDetails;
import com.cricteam.models.UserDetails;

public interface UserDetailService {
	UserDetails saveUser(UserDetails userdetail);
	UserDetails getUserViaUserId(int userId);
	UserCompleteDetails getUserCompleteDetailsViaUserId(int userId);

	UserCompleteDetails getUserViaMobileNo(String mobileNo);
	UserDetails updateUser(UserDetails userDetails, String userId);
	UserDetails updateUserDetails(UserDetails userDetails);
}
