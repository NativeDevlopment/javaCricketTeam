package com.cricteam.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.hibernate.Session;
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
	
	@Override
	public UserDetails updateUserDetails(UserDetails userDetails) {
		try{
			entityManager.createNativeQuery("update user_details ud set ud.mobile_no = '"+userDetails.getMobileNo()+"', ud.device_id = '"+userDetails.getDeviceId()+
					"', ud.device_token = '"+userDetails.getDeviceToken()+"', ud.device_type = '"+userDetails.getDeviceType()+"', ud.name = '"+userDetails.getName()+
					"', ud.user_address = '"+userDetails.getUserAddress()+"', ud.user_email = '"+userDetails.getUserEmail()+"', ud.user_image_url = '"+userDetails.getUserImageUrl()+
					"', ud.user_lat = '"+userDetails.getUserLat()+"', ud.user_long = '"+userDetails.getUserLong()+"'  where ud.user_id="+userDetails.getUserId()).setParameter("userDetails", "userDetails").executeUpdate();
			/*UserDetails userDetail= (UserDetails)entityManager.find(UserDetails.class ,1);
			Session session = (Session) entityManager.find(UserDetails.class, 1);
			userDetail.
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();//Only save or update
			session.saveOrUpdate(userDetails);
			System.out.println("Data saved into the DB. ID - "+userDetails.getUserId());
			session.getTransaction().commit();
			session.close();*/
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return userRepository.getUserByPhone(userDetails.getMobileNo()); 
	}
}
