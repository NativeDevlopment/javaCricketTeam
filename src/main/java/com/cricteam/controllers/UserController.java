package com.cricteam.controllers;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cricteam.exception.ResourceNotFroundException;
import com.cricteam.models.Response;
import com.cricteam.models.UserCompleteDetails;
import com.cricteam.models.UserDetails;
import com.cricteam.service.UserDetailService;

@RestController
public class UserController {

	@Autowired
	private UserDetailService  userdetailService;
	
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
	public UserCompleteDetails getUser(int userId) {
		if(userId == 0) {
			throw new ResourceNotFroundException("User not found with this id");
		}
		return userdetailService.getUserCompleteDetailsViaUserId(userId);
	}
}
