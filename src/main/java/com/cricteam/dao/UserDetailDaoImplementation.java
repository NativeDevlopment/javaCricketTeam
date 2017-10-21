package com.cricteam.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cricteam.models.TeamDetails;
import com.cricteam.models.UserCompleteDetails;
import com.cricteam.models.UserDetails;
import com.cricteam.repository.TeamDetailsRepository;
import com.cricteam.repository.UserRepository;

@Service("userDetailDao")
@Repository
@Transactional
public class UserDetailDaoImplementation implements UserDetailDao {
	@Autowired 
	UserRepository<UserDetails> userRepository ;
	@Autowired 
	TeamDetailsRepository<TeamDetails> teamDetailsRepositry ;
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public UserDetails getUserViaUserId(int userId) {
		// TODO Auto-generated method stub
		return userRepository.getUserById(userId);
	}
	@Override
	public UserCompleteDetails getUserViaMobileNo(String mobileNo) {
		// TODO Auto-generated method stub
		UserCompleteDetails completeDetails= new UserCompleteDetails();
		completeDetails.setUserDetails(userRepository.getUserByPhone(mobileNo));
		completeDetails.setUserTeamList(teamDetailsRepositry.findTeamViaUserId(completeDetails.getUserDetails().getUserId()));
		
		return completeDetails;
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
	@Transactional
	@Override
	public UserDetails updateUserDetails(UserDetails userDetails) {
		
		UserDetails details=	entityManager.merge(SetDataForUpdate(userDetails));
		entityManager.flush();
		return details;
	}
	private UserDetails SetDataForUpdate(UserDetails userDetails) {
		// TODO Auto-generated method stub
		UserDetails getExsitingData=userRepository.getUserById(userDetails.getUserId());
		if(userDetails.getDeviceId()!=null&& !userDetails.getDeviceId().equalsIgnoreCase("")){
			getExsitingData.setDeviceId(userDetails.getDeviceId());
		}
		if(userDetails.getDeviceId()!=null&& !userDetails.getDeviceId().equalsIgnoreCase("")){
			getExsitingData.setDeviceId(userDetails.getDeviceId());
		}
		if(userDetails.getDeviceToken()!=null&& !userDetails.getDeviceToken().equalsIgnoreCase("")){
			getExsitingData.setDeviceToken(userDetails.getDeviceToken());
		}
		if(userDetails.getDeviceType()!=null&& !userDetails.getDeviceType().equalsIgnoreCase("")){
			getExsitingData.setDeviceType(userDetails.getDeviceType());
		}
		if(userDetails.getMobileNo()!=null&& !userDetails.getMobileNo().equalsIgnoreCase("")){
			getExsitingData.setMobileNo(userDetails.getMobileNo());
		}
		if(userDetails.getName()!=null&& !userDetails.getName().equalsIgnoreCase("")){
			getExsitingData.setName(userDetails.getName());
		}
		if(userDetails.getUserAddress()!=null&& !userDetails.getUserAddress().equalsIgnoreCase("")){
			getExsitingData.setUserAddress(userDetails.getUserAddress());
		}
		if(userDetails.getUserEmail()!=null&& !userDetails.getUserEmail().equalsIgnoreCase("")){
			getExsitingData.setUserEmail(userDetails.getUserEmail());
		}
		if(userDetails.getUserImageUrl()!=null&& !userDetails.getUserImageUrl().equalsIgnoreCase("")){
			getExsitingData.setUserImageUrl(userDetails.getUserImageUrl());
		}
		if(userDetails.getUserLat()!=null&& !userDetails.getUserLat().equalsIgnoreCase("")){
			getExsitingData.setUserLat(userDetails.getUserLat());
		}
		if(userDetails.getUserLong()!=null&& !userDetails.getUserLong().equalsIgnoreCase("")){
			getExsitingData.setUserLong(userDetails.getUserLong());
		}
		
		return getExsitingData;
	}
	@Override
	public UserCompleteDetails getUserCompleteDetailsViaUserId(int userId) {
		// TODO Auto-generated method stub
		UserCompleteDetails completeDetails= new UserCompleteDetails();
		completeDetails.setUserDetails(userRepository.getUserById(userId));
		completeDetails.setUserTeamList(teamDetailsRepositry.findTeamViaUserId(userId));
		return completeDetails;
	}
}
