package com.cricteam.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Entity
@Table(name="OTP")
public class Otp {

	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getOtp() {
		return otp;
	}


	public void setOtp(String otp) {
		this.otp = otp;
	}


	public String getCreated_time() {
		return created_time;
	}


	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}


	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String mobileNo;
	
	@Column(name="otp")
	private String otp;
	@Column(name="created_time")
	private String created_time;
	
	public Otp(){
	
	}
	public Otp(String mobileNo, String otp, String created_time) {
		super();
		this.mobileNo = mobileNo;
		this.otp = otp;
		this.created_time = created_time;
	}


	@Override
	public String toString(){
		 return "{id:" + id + ", mobileNo:" + mobileNo + ", otp:"
				+ otp + ", createdTime:" + created_time + "}";
	}
}
