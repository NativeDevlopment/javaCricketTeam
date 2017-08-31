package com.cricteam.controllers;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cricteam.models.Otp;
import com.cricteam.models.Response;
import com.cricteam.service.OtpService;

@Controller
public class OtpController {
	@Autowired
	OtpService otpService;
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
	      otp.setOtp(code);
	      response.statusCode=HttpURLConnection.HTTP_OK;
	      response.message="Otp sucessfully sent to Mobile no";
	      response.data=otp;
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

}
