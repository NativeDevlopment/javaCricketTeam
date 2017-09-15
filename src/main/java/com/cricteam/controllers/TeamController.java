package com.cricteam.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cricteam.TeamCircleStatusEnum;
import com.cricteam.exception.ResourceNotFroundException;
import com.cricteam.models.AddPlayerRequest;
import com.cricteam.models.FindTeamRequest;
import com.cricteam.models.PlayerDetails;
import com.cricteam.models.Response;
import com.cricteam.models.TeamCircle;
import com.cricteam.models.TeamCircleRequest;
import com.cricteam.models.TeamDetails;
import com.cricteam.models.TeamRequest;
import com.cricteam.models.UserDetails;
import com.cricteam.service.AddPlayerService;
import com.cricteam.service.PredefinedService;
import com.cricteam.service.TeamCircleService;
import com.cricteam.service.TeamService;
import com.cricteam.service.UserDetailService;
import com.google.gson.JsonObject;

@RestController
public class TeamController {
	@Autowired TeamService teamService;
	@Autowired UserDetailService userService;
	@Autowired AddPlayerService addplayerService;

	@Autowired TeamCircleService teamCircleService;
	@Autowired PredefinedService predefinedService;
	public final  String AUTH_KEY_FCM = "AAAAZklwOYU:APA91bHBXgKU9GEd-dr5SPNAplf6sDMxap8LloEj4VelASsaDgcHa7yKUx-w05SVwilhJx00_z_3QqF5Xnikw38aBrz1ISDdaLO_iN-HQlitwCrxWoB5unoVk8Gxxak-wBxIdqt19KGC";
    public final  String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
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
	
	 @RequestMapping(value="/findTeamCircle",method = RequestMethod.POST, produces = "application/json")
	  @ResponseBody
	  public Response findTeamCircle(@RequestBody FindTeamRequest teamRequst,int pageNo,int pageSize) {
		 
		 Response response= new Response();
	  
	    if(teamRequst.getUserId()>0){
	    try {
	    	response.message="scuess";
	      response.data=teamService.findTeamCircle(teamRequst ,teamRequst.getLatitude(), teamRequst.getLongitude(),pageNo,pageSize);
	      
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
	 @RequestMapping(value="/getTeamDetails",method = RequestMethod.GET, produces = "application/json")
		
	 public Response getTeamDetails(int ownTeamId,int teamId,int userId) {
		 
		 Response response= new Response();
	    
	    try {
	    	 response.statusCode=HttpURLConnection.HTTP_OK;
	    	 response.message="sucess";
	    	 
	    	 response.data=teamService.getTeamViaTeamId(userId, teamId,ownTeamId);
	   }  
	    
	    
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		      response.message="Internal server error";
	    }
	    return response;
	  }
	 @RequestMapping(value="/teamRequest",method = RequestMethod.POST, produces = "application/json")
		
	 public Response sendTeamRequest(@RequestBody TeamCircleRequest circleRequest) {
		 Response response= new Response();
		 JsonObject data = new JsonObject();
	    try {
	    	 response.statusCode=HttpURLConnection.HTTP_OK;
	    	 response.message="sucess";
	    	 TeamCircle  teamCircle= new TeamCircle();
	    	 if(circleRequest.getRequestId()!=0){
		    	 teamCircle=teamCircleService.getTeamCircleDetails(circleRequest.getRequestId());
		    	 if(circleRequest.getRequestAction().equalsIgnoreCase(TeamCircleStatusEnum.ACCEPT.name())){
		    	 if(teamCircle.getTeamReceiverId().getTeamId()==circleRequest.getReceiverId()){
		    		
		    		 if(circleRequest.getRequestAction().equalsIgnoreCase(TeamCircleStatusEnum.ACCEPT.name())){
		    			 teamCircle.setTeamCircleStatusId(predefinedService.findCircleStatus(TeamCircleStatusEnum.ACCEPT.name())); 
		    			 response.data=teamCircleService.sendRequest(teamCircle);
		    			 data.addProperty("to", teamCircle.getTeamSenderId().getUserDetails().getDeviceToken());
		    		        JsonObject info = new JsonObject();
		    		        info.addProperty("name", teamCircle.getTeamReceiverId().getTeamName()); // Notification title
		    		        info.addProperty("message", "Ready to  start talk with you .");
		    		        info.addProperty("imageUrl",  teamCircle.getTeamReceiverId().getTeamLogoUrl());
		    		        info.addProperty("requestId",  teamCircle.getTeamCircleId());
		    		        info.addProperty("receiverTeamId",  teamCircle.getTeamReceiverId().getTeamId());
		    		        info.addProperty("type", "accept");
		    		        info.addProperty("receiverUserId",  teamCircle.getTeamReceiverId().getUserDetails().getUserId());// Notification body
		    		        data.add("data", info);
		    		 }
		    		 
		    	 }else{
		    		 response.statusCode=HttpURLConnection.HTTP_NOT_AUTHORITATIVE;
			    	 response.message="your team acceptance id mismatch";
		    	 }
		    	 }else  if(circleRequest.getRequestAction().equalsIgnoreCase(TeamCircleStatusEnum.REJECT.name()))
		    	 {
		    		 if(teamCircle.getTeamReceiverId().getTeamId()==circleRequest.getReceiverId()){
				    		
			    		
			    			 teamCircle.setTeamCircleStatusId(predefinedService.findCircleStatus(TeamCircleStatusEnum.REJECT.name())); 
			    			 response.data=teamCircleService.sendRequest(teamCircle);
			    		 
			    		 
			    	 }else{
			    		 response.statusCode=HttpURLConnection.HTTP_NOT_AUTHORITATIVE;
				    	 response.message="your team acceptance id mismatch";
			    	 }
		    	
		    	 }
		    	 }else{
	    	
	    	 if(circleRequest.getReceiverId()!=0 && circleRequest.getReceiverUserId()!=0){
	    		 teamCircle.setTeamReceiverId(teamService.getTeamViaTeamId(circleRequest.getReceiverUserId(), circleRequest.getReceiverId()));
	    	 }
	    	 if(circleRequest.getSenderId()!=0 && circleRequest.getSenderUserId()!=0){
	    		 teamCircle.setTeamSenderId(teamService.getTeamViaTeamId(circleRequest.getSenderUserId(), circleRequest.getSenderId()));
	    	 }
	    	 if(circleRequest.getRequestAction()!=null)
	    	 {
	    		 if(circleRequest.getRequestAction().equalsIgnoreCase(TeamCircleStatusEnum.SEND.name())){
	    			 teamCircle.setTeamCircleStatusId(predefinedService.findCircleStatus(TeamCircleStatusEnum.SEND.name())); 
	    		TeamCircle result=	 teamCircleService.sendRequest(teamCircle);
	    			 response.data=result;
	    		        data.addProperty("to", result.getTeamReceiverId().getUserDetails().getDeviceToken());
	    		        JsonObject info = new JsonObject();
	    		        info.addProperty("name", result.getTeamSenderId().getTeamName()); // Notification title
	    		        info.addProperty("message", "Requested to want play match");
	    		        info.addProperty("imageUrl",  result.getTeamSenderId().getTeamLogoUrl());
	    		        info.addProperty("requestId",  result.getTeamCircleId());
	    		        info.addProperty("senderTeamId",  result.getTeamSenderId().getTeamId());
	    		        info.addProperty("type", "request");
	    		        info.addProperty("senderUserId",  result.getTeamSenderId().getUserDetails().getUserId());// Notification body
	    		        data.add("data", info);
	    		 }
	    	 }
	    	
		    	 } 
	    	 
	   }  
	    
	   
	    catch (Exception ex) {
	    	 response.statusCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
		      response.message="Internal server error";
	    }
	    try {
			pushFCMNotification(data.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return response;
	  }

	 
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
	 
	 
	 @RequestMapping(value="/updatePlayer",method = RequestMethod.POST, produces = "application/json")
		
	 public PlayerDetails updateplayer(@RequestBody PlayerDetails addplayerRequest) {
		
		 if(addplayerRequest.getPlayerId()==0){
				throw new ResourceNotFroundException("User not found with this id");

		 }else{
			return addplayerService.addPlayer(addplayerRequest); 
		 }
		
	  }
	 
	 public  void pushFCMNotification(String data) throws Exception {

	        String authKey = AUTH_KEY_FCM; // You FCM AUTH key
	        String FMCurl = API_URL_FCM;

	        URL url = new URL(FMCurl);
	        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

	        conn.setUseCaches(false);
	        conn.setDoInput(true);
	        conn.setDoOutput(true);

	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "key=" + authKey);
	        conn.setRequestProperty("Content-Type", "application/json");
	        	
	        	

	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        wr.write(data);
	        wr.flush();
	        wr.close();

	        int responseCode = conn.getResponseCode();
	        System.out.println("Response Code : " + responseCode);

	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();

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
	
	
	
	
}
