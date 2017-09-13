package com.cricteam.controllers;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cricteam.models.AddPlayerRequest;
import com.cricteam.models.FindTeamRequest;
import com.cricteam.models.Otp;
import com.cricteam.models.Response;
import com.cricteam.models.TeamDetails;
import com.cricteam.models.TeamRequest;
import com.cricteam.models.UserDetails;
import com.cricteam.models.VerifyOtp;
import com.cricteam.service.AddPlayerService;
import com.cricteam.service.TeamService;
import com.cricteam.service.UserDetailService;

@RestController
@Controller
public class TeamController {
	@Autowired TeamService teamService;
	@Autowired UserDetailService userService;
	@Autowired AddPlayerService addplayerService;

	 @RequestMapping(value="/findTeam",method = RequestMethod.POST, produces = "application/json")
	  @ResponseBody
	  public Response findTeamViaLatLong(@RequestBody FindTeamRequest teamRequst,int pageNo,int pageSize) {
		 
		 Response response= new Response();
	  
	    if(teamRequst.getUserId()>0){
	    try {
	 response.message="scuess";
	      response.data=teamService.findTeamViaLatLong(teamRequst ,teamRequst.getLatitude(), teamRequst.getLongitude(),pageNo,pageSize);
	      
	      response.statusCode=HttpURLConnection.HTTP_OK;
	    }
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		      response.message=ex.getStackTrace().toString();
		      ex.printStackTrace();
	    }}else{
	    	 response.statusCode=HttpURLConnection.HTTP_LENGTH_REQUIRED;
		      response.message="Fields Required.";
	    }
	    return response;
	  }
	 @RequestMapping(value="/SaveTeam",method = RequestMethod.POST, produces = "application/json")
	
	 public Response SaveTeam(@RequestBody TeamRequest teamRequest) {
		 
		 Response response= new Response();
	    
	    try {
	   UserDetails userDetails= 	userService.getUserViaUserId(teamRequest.getUserId());
	   if(userDetails!=null&& userDetails.getUserId()!=0){
	      TeamDetails teamDetails=new TeamDetails();
	      teamDetails.setTeamAddress(teamRequest.getTeamAddress());
	      teamDetails.setTeamDesc(teamRequest.getTeamDesc());
	      teamDetails.setTeamName(teamRequest.getTeamName());
	      teamDetails.setTeamLat(Double.valueOf(teamRequest.getTeamLat()));
	      teamDetails.setTeamLong(Double.valueOf(teamRequest.getTeamLong()));
	      teamDetails.setTeamLogoUrl(teamRequest.getTeamLogoUrl());

	      teamDetails.setUserDetails(userDetails);
	    	 response.statusCode=HttpURLConnection.HTTP_OK;
	    	 response.message="sucess";
	    	 
	    	 response.data=teamService.saveTeam(teamDetails);
	   }else{
		   response.statusCode=HttpURLConnection.HTTP_NOT_FOUND;
	    	 response.message="wrong user id";
	    	 
	   }

	      }
	    
	    
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		      response.message="Internal server error";
	    }
	    return response;
	  }
	 @RequestMapping(value="/getTeamDetails",method = RequestMethod.GET, produces = "application/json")
		
	 public Response getTeamDetails(int teamId,int userId) {
		 
		 Response response= new Response();
	    
	    try {
	    	 response.statusCode=HttpURLConnection.HTTP_OK;
	    	 response.message="sucess";
	    	 
	    	 response.data=teamService.getTeamViaTeamId(userId, teamId);
	   }  
	    
	    
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		      response.message="Internal server error";
	    }
	    return response;
	  }
	 @RequestMapping(value="/sendTeamRequest",method = RequestMethod.POST, produces = "application/json")
		
	 public Response sendTeamRequest(int teamId,int userId) {
		 Response response= new Response();
	    try {
	    	 response.statusCode=HttpURLConnection.HTTP_OK;
	    	 response.message="sucess";
	    	 
	    	 response.data=teamService.getTeamViaTeamId(userId, teamId);
	   }  
	    
	    
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		      response.message="Internal server error";
	    }
	    return response;
	  }
	 /**
		 * Add player controller for add player to team , check player details and team details class inside model package 
		 * exception come  Caused by: org.hibernate.PersistentObjectException: detached entity passed to persist: com.cricteam.models.TeamDetails


request :- {
"userId":1,
"teamId":1,
"playerList":[
{
"playerName":"Amar",
"playerMobile":54545454,
"playerTypes":{
"playerTypeId":0
},
"teamDetails":{
"teamId":1
},
"leaderShip":"C"
}
]
}
		 */
	 @RequestMapping(value="/addPlayers",method = RequestMethod.POST, produces = "application/json")
		
	 public Response AddPlayers(@RequestBody AddPlayerRequest addplayerRequest) {
		 Response response= new Response();
	    try {
	    	 response.statusCode=HttpURLConnection.HTTP_OK;
	    	 response.message="sucess";
	    	  response.data=addplayerService.addPlayers(addplayerRequest.getPlayerList());
	    
	    }
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		      response.message="Internal server error";
		      ex.printStackTrace();
	    }
	    return response;
	  }
}
