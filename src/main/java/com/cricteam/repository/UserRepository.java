package com.cricteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cricteam.models.UserDetails;


public interface UserRepository<T> extends UserBaseRepository<UserDetails>,JpaRepository<UserDetails, String> {

	@Query("select u from UserDetails u where u.userId =:mid  ")
	UserDetails getUserById(@Param("mid") int mid);
	
	@Query("select u from UserDetails u where u.userEmail =:mid")
	UserDetails getUserByEMail(@Param("mid") String mid);

	@Query("select u from UserDetails u where u.mobileNo =:mobileNo")
	UserDetails getUserByPhone(@Param("mobileNo")String mobileNo);
	
}
