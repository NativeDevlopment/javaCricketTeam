package com.cricteam.models;

import java.util.ArrayList;
import java.util.List;

import com.cricteam.controllers.error.FieldErrorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

	public int statusCode;
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String message;
	public Object data;
	private boolean success;
	private String path;
	private List<FieldErrorDTO> errors;

	public Response() {
	}

	public Response(Object body, String message, String path) {
		this.success = true;
		this.data = body;
		this.message = message;
		this.path = path;
		this.statusCode = 200;
	}

	public void addError(FieldErrorDTO error) {
		if (errors == null) {
			errors = new ArrayList<FieldErrorDTO>();
		}
		errors.add(error);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<FieldErrorDTO> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldErrorDTO> errors) {
		this.errors = errors;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
