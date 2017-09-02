package com.cricteam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cricteam.dao.OtpDao;
import com.cricteam.models.Otp;
import com.cricteam.models.VerifyOtp;
@Service("otpService")
@Repository
public class OtpServiceImplementation implements OtpService {
@Autowired
OtpDao otpDao;
	@Override
	public String saveOtp(Otp otp) {
		// TODO Auto-generated method stub
		return otpDao.saveOtp(otp);
	}
	@Override
	public boolean verifyOtp(VerifyOtp verifyOtp) {
		// TODO Auto-generated method stub
		return otpDao.verifyOtp(verifyOtp);
	}

}
