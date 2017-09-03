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
import com.cricteam.models.UserDetails;
import com.cricteam.models.VerifyOtp;
import com.cricteam.service.OtpService;
import com.cricteam.service.UserDetailService;
import com.cricteam.service.UserDetailsServiceImplementation;
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
	    if(mobileNo.length()>10){
	    try {
	      otp = new Otp(mobileNo,"","");
	      code = otpService.saveOtp(otp);
	      response.statusCode=HttpURLConnection.HTTP_OK;
	      response.message="Otp sucessfully sent to Mobile no";
	    }
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		      response.message="Internal server error";
	    }}else{
	    	 response.statusCode=HttpURLConnection.HTTP_LENGTH_REQUIRED;
		      response.message="Please Enter Valid Mobile No.";
	    }
	    return response;
	  }
	 @RequestMapping(value="/veryifyOtp",method = RequestMethod.POST, produces = "application/json")
	
	 public Response veryifyOtp(@RequestBody VerifyOtp verifyOtp) {
		 
		 Response response= new Response();
	    boolean isNumberVerified;
	    if(verifyOtp.getMobileNo().length()>10){
	    try {
	      isNumberVerified = otpService.verifyOtp(verifyOtp);
	      if(isNumberVerified){
	    	  UserDetails details=userdetailService.getUserViaMobileNo(verifyOtp.getMobileNo());
	    	  	if(details!=null&&details.getUserId()!=0){
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
	 
	 @RequestMapping(value="/updateUser",method = RequestMethod.POST, produces = "application/json")
		
	 public Response updateUser(@RequestBody UserDetails userDetails) {
		 
		Response response= new Response();
	    try {
    	  	UserDetails userdetail= new UserDetails();
    	  	userdetail.setUserId(userDetails.getUserId());
   	    	userdetail.setMobileNo(userDetails.getMobileNo());
   	    	userdetail.setDeviceId(userDetails.getDeviceId());
   	    	userdetail.setDeviceToken(userDetails.getDeviceToken());
   	    	userdetail.setDeviceType(userDetails.getDeviceType());
   	    	userdetail.setName(userDetails.getName());
   	    	userdetail.setUserAddress(userDetails.getUserAddress());
   	    	userdetail.setUserEmail(userDetails.getUserEmail());
   	    	userdetail.setUserImageUrl(userDetails.getUserImageUrl());
   	    	userdetail.setUserLat(userDetails.getUserLat());
   	    	userdetail.setUserLong(userDetails.getUserLong());
   	    	
   	    	response.data=  userdetailService.updateUserDetails(userdetail);
   	    	response.statusCode=HttpURLConnection.HTTP_OK;
   		    response.message="User Updated SuccessFully";
	    }
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		     response.message="Internal server error";
	    }
	    return response;
	  }
	 
	 public static void main(String arg[]) {
		 Response response= new Response();
		 UserDetailService  userdetailService = new UserDetailsServiceImplementation();
		    try {
	    	  	UserDetails userdetail= new UserDetails();
	    	  	userdetail.setUserId(1);
	   	    	userdetail.setMobileNo("9717034684");
	   	    	userdetail.setDeviceId("");
	   	    	userdetail.setDeviceToken("");
	   	    	userdetail.setDeviceType("");
	   	    	userdetail.setName("sjdh");
	   	    	userdetail.setUserAddress("Laxmi Nagar");
	   	    	userdetail.setUserEmail("sgjh@jkhd.com");
	   	    	userdetail.setUserImageUrl("");
	   	    	userdetail.setUserLat("");
	   	    	userdetail.setUserLong("");
	   	    	
	   	    	response.data=  userdetailService.updateUserDetails(userdetail);
	   	    	response.statusCode=HttpURLConnection.HTTP_OK;
	   		    response.message="User Updated SuccessFully";
		    }
		    catch (Exception ex) {
		    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
			     response.message="Internal server error";
		    }
	 }
}
