package com.cricteam.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="user_details", uniqueConstraints = @UniqueConstraint(name = "uk_user_mobile_no", columnNames = "mobileNo"))
public class UserDetails {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	
	private String mobileNo;
	
	@Column(name="device_id")
	private String deviceId;
	
	@Column(name="device_token")
	private String deviceToken;
	
	@Column(name="device_type")
	private String deviceType;
	
	@Column(name="name")
	private String name;
	
	@Column(name="user_lat")
	private String userLat;
	
	@Column(name="user_long")
	private String userLong;
	
	@Column(name="user_address")
	private String userAddress;
	
	@Column(name="user_image_url")
	private String userImageUrl;
	
	@Column(name="user_email")
	private String userEmail;
	
	public UserDetails(){
		
	}
	
	public UserDetails(int userId, String mobileNo, String deviceId, String deviceToken, String deviceType, String name, String userLat, String userLong, String userAddress, String userImageUrl, String userEmail ) {
		super();
		this.userId = userId;
		this.mobileNo = mobileNo;
		this.deviceId = deviceId;
		this.deviceToken = deviceToken;
		this.deviceType = deviceType;
		this.name = name;
		this.userLat = userLat;
		this.userLong = userLong;
		this.userAddress = userAddress;
		this.userImageUrl = userImageUrl;
		this.userEmail = userEmail;
	}


	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}


	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}


	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}


	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	/**
	 * @return the deviceToken
	 */
	public String getDeviceToken() {
		return deviceToken;
	}


	/**
	 * @param deviceToken the deviceToken to set
	 */
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}


	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}


	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the userLat
	 */
	public String getUserLat() {
		return userLat;
	}


	/**
	 * @param userLat the userLat to set
	 */
	public void setUserLat(String userLat) {
		this.userLat = userLat;
	}


	/**
	 * @return the userLong
	 */
	public String getUserLong() {
		return userLong;
	}


	/**
	 * @param userLong the userLong to set
	 */
	public void setUserLong(String userLong) {
		this.userLong = userLong;
	}


	/**
	 * @return the userAddress
	 */
	public String getUserAddress() {
		return userAddress;
	}


	/**
	 * @param userAddress the userAddress to set
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}


	/**
	 * @return the userImageUrl
	 */
	public String getUserImageUrl() {
		return userImageUrl;
	}


	/**
	 * @param userImageUrl the userImageUrl to set
	 */
	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}


	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}


	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	@Override
	public String toString(){
		 return "{userId:" + userId + ", mobileNo:" + mobileNo + ", deviceId:"
				+ deviceId + ", deviceToken:" + deviceToken + ", deviceType:" 
				+ deviceType + ", name:" + name + ", userLat:" + userLat + 
				", userLong:" + userLong + ", userAddress:" + userAddress + 
				", userImageUrl:" + userImageUrl + ", userEmail:" + userEmail + "}";
	}
}
