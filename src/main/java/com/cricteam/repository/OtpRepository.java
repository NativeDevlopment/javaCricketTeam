package com.cricteam.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cricteam.models.Otp;
@Transactional
public interface OtpRepository extends JpaRepository<Otp, Long>{
	

}
