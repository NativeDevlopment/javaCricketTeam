package com.cricteam.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cricteam.models.Otp;
@Transactional
public interface OtpRepository extends JpaRepository<Otp, String>{
	@Query("select o from Otp o where o.mobileNo =:mobileNo")
	Otp	getOtpViaMobileNo(@Param("mobileNo") String mobileNo);
	

}
