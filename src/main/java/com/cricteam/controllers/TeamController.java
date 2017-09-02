package com.cricteam.controllers;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cricteam.models.FindTeamRequest;
import com.cricteam.models.Otp;
import com.cricteam.models.Response;
import com.cricteam.models.TeamDetails;
import com.cricteam.models.UserDetails;
import com.cricteam.models.VerifyOtp;
import com.cricteam.service.TeamService;

@RestController
@Controller
public class TeamController {
	@Autowired TeamService teamService;
	 @RequestMapping(value="/findTeam",method = RequestMethod.POST, produces = "application/json")
	  @ResponseBody
	  public Response findTeamViaLatLong(@RequestBody FindTeamRequest teamRequst) {
		 
		 Response response= new Response();
	  
	    if(!teamRequst.getUserId().isEmpty()){
	    try {
	     
	      response.statusCode=HttpURLConnection.HTTP_OK;
	      response.message="scuess";
	      response.data=teamService.findTeamViaLatLong(teamRequst.getLatitude(), teamRequst.getLongitude());
	    }
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		      response.message="Internal server error";
	    }}else{
	    	 response.statusCode=HttpURLConnection.HTTP_LENGTH_REQUIRED;
		      response.message="Fields Required.";
	    }
	    return response;
	  }
	 @RequestMapping(value="/SaveTeam",method = RequestMethod.POST, produces = "application/json")
	
	 public Response veryifyOtp(@RequestBody TeamDetails teamDetails) {
		 
		 Response response= new Response();
	    
	    try {
	      
	    	 response.statusCode=HttpURLConnection.HTTP_OK;
	    	 response.message="sucess";
	    	 response.data=teamService.saveTeam(teamDetails);

	      }
	    
	    
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		      response.message="Internal server error";
	    }
	    return response;
	  }
}
