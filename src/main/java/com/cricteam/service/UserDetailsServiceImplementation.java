package com.cricteam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cricteam.dao.UserDetailDao;
import com.cricteam.models.UserDetails;
@Service("userDetailService")
@Repository
public class UserDetailsServiceImplementation implements UserDetailService {
@Autowired
UserDetailDao userdetailDao;
	@Override
	public UserDetails saveUser(UserDetails otp) {
		// TODO Auto-generated method stub
		return userdetailDao.saveUser(otp);
	}

	@Override
	public UserDetails getUserViaUserId(int userId) {
		// TODO Auto-generated method stub
		return userdetailDao.getUserViaUserId(userId);
	}

	@Override
	public UserDetails getUserViaMobileNo(String mobileNo) {
		// TODO Auto-generated method stub
		return userdetailDao.getUserViaMobileNo(mobileNo);
	}

	@Override
	public UserDetails updateUser(UserDetails userDetails, String userId) {
		// TODO Auto-generated method stub
		return userdetailDao.updateUser(userDetails, userId);
	}

	@Override
	public UserDetails updateUserDetails(UserDetails userDetails) {
		// TODO Auto-generated method stub
		return userdetailDao.updateUserDetails(userDetails);
	}

}
