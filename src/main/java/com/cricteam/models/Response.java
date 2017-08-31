package com.cricteam.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

	public int statusCode;
	public String message;
	public Object data;
	
}
