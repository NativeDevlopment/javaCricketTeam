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
public class UserController {
	
	@Autowired
	UserDetailService  userdetailService;
	
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
   	    	
   	    	response.data=  userdetailService.updateUserDetails(userDetails);
   	    	response.statusCode=HttpURLConnection.HTTP_OK;
   		    response.message="User Updated SuccessFully";
	    }
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		     response.message="Internal server error";
	    }
	    return response;
	  }
	 
	 @RequestMapping(value="/getUser",method = RequestMethod.GET, produces = "application/json")
		
	 public Response updateUser(int userId) {
		 
		Response response= new Response();
	    try {
    	  	
   	    	
   	    	response.data=  userdetailService.getUserViaUserId(userId);
   	    	response.statusCode=HttpURLConnection.HTTP_OK;
   		    response.message="get user details sucessfully";
	    }
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		     response.message="Internal server error";
	    }
	    return response;
	  }
}
