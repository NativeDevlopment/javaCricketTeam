package com.cricteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cricteam.models.Otp;
import com.cricteam.service.OtpService;

@Controller
public class OtpController {
	@Autowired
	OtpService otpService;
	 @RequestMapping("/sendOtp")
	  @ResponseBody
	  public String create(String mobileNo) {
	    Otp otp = null;
	    String code="";
	    try {
	      otp = new Otp(mobileNo,"9650",String.valueOf(System.currentTimeMillis()));
	      code = otpService.saveOtp(otp);
	    }
	    catch (Exception ex) {
	      return "Error creating the user: " + ex.toString();
	    }
	    return "your otp code is"+ " "+code;
	  }

}
