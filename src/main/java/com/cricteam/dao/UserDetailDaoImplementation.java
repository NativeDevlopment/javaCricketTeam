package com.cricteam.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cricteam.models.UserDetails;
import com.cricteam.repository.UserRepository;

@Service("userDetailDao")
@Repository
@Transactional
public class UserDetailDaoImplementation implements UserDetailDao {
	@Autowired 
	UserRepository<UserDetails> userRepository ;
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public UserDetails getUserViaUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UserDetails getUserViaMobileNo(String mobileNo) {
		// TODO Auto-generated method stub
		return userRepository.getUserByPhone(mobileNo);
	}
	@Override
	public UserDetails updateUser(UserDetails userDetails, String userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UserDetails saveUser(UserDetails userdetails) {
		// TODO Auto-generated method stub
		return  userRepository.save(userdetails);
	}
}
