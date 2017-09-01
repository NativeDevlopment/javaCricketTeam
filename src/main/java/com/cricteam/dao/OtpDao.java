package com.cricteam.dao;

import com.cricteam.models.Otp;
import com.cricteam.models.VerifyOtp;

public interface OtpDao {
String	saveOtp(Otp otp);
boolean verifyOtp(VerifyOtp verifyOtp);

}
