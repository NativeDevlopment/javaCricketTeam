package com.cricteam.controllers;


import org.apache.commons.httpclient.util.HttpURLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cricteam.models.Otp;
import com.cricteam.models.Response;
import com.cricteam.models.UserCompleteDetails;
import com.cricteam.models.UserDetails;
import com.cricteam.models.VerifyOtp;
import com.cricteam.service.OtpService;
import com.cricteam.service.UserDetailService;
@RestController
@Controller

public class OtpController {
	@Autowired
	OtpService otpService;
	@Autowired
	UserDetailService  userdetailService;
	 @RequestMapping(value="/sendOtp",method = RequestMethod.GET, produces = "application/json")
	  @ResponseBody
	  public Response createOtp(String mobileNo) {
		 
		 Response response= new Response();
	    Otp otp = null;
	    String code="";
	    if(mobileNo.length()>=10){
	    try {
	      otp = new Otp(mobileNo,"","");
	      code = otpService.saveOtp(otp);
	      response.statusCode=HttpURLConnection.HTTP_OK;
	      response.message="Otp sucessfully sent to Mobile no";
	      response.data=code;

	    }
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		      response.message="Internal server error";
	    }}else{
	    	 response.statusCode=HttpURLConnection.HTTP_LENGTH_REQUIRED;
		      response.message="Please Enter Valid Mobile No.";
		      response.data=code;
	    }
	    return response;
	  }
	 @RequestMapping(value="/veryifyOtp",method = RequestMethod.POST, produces = "application/json")
	
	 public Response veryifyOtp(@RequestBody VerifyOtp verifyOtp) {
		 
		 Response response= new Response();
	    boolean isNumberVerified;
	    if(verifyOtp.getMobileNo().length()>=10){
	    try {
	      isNumberVerified = otpService.verifyOtp(verifyOtp);
	      if(isNumberVerified){
	    	  UserCompleteDetails details=userdetailService.getUserViaMobileNo(verifyOtp.getMobileNo());
	    	  	if(details!=null&&details.getUserDetails()!=null&&details.getUserDetails().getUserId()!=0){
	    	  		 response.data= details;
		   	    	  response.statusCode=HttpURLConnection.HTTP_OK;
		   		      response.message="Number Verified SuccessFully";
	    	  	}else{
	    	  		 UserDetails userdetail= new UserDetails();
	   	    	  userdetail.setMobileNo(verifyOtp.getMobileNo());
	   	    	  userdetail.setDeviceId(verifyOtp.getDeviceId());
	   	    	  userdetail.setDeviceToken(verifyOtp.getDeviceToken());
	   	    	  userdetail.setDeviceType(verifyOtp.getDeviceType());
	   	    	  response.data=  userdetailService.saveUser(userdetail);
	   	    	  response.statusCode=HttpURLConnection.HTTP_OK;
	   		      response.message="Number Verified SuccessFully";
	    	  	}
	    	 

	      }else{
	    	  response.data=null;
	    	  response.statusCode=HttpURLConnection.HTTP_UNAUTHORIZED;
		      response.message="Number Verification Failed";
	      }
	    
	    }
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		      response.message="Internal server error";
	    }}else{
	    	 response.statusCode=HttpURLConnection.HTTP_LENGTH_REQUIRED;
		      response.message="field required";
	    }
	    return response;
	  }
	 
	
}
