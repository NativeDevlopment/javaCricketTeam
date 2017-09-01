package com.cricteam.service;

import com.cricteam.models.Otp;
import com.cricteam.models.VerifyOtp;

public interface OtpService {
	String	saveOtp(Otp otp);
	boolean verifyOtp(VerifyOtp verifyOtp);

}
