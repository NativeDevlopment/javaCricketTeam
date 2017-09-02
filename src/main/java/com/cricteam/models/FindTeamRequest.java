package com.cricteam.models;

public class FindTeamRequest {
String userId;
String latitude;
String longitude;
int pageNo;
int pageSize;
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getLatitude() {
	return latitude;
}
public void setLatitude(String latitude) {
	this.latitude = latitude;
}
public String getLongitude() {
	return longitude;
}
public void setLongitude(String longitude) {
	this.longitude = longitude;
}
public int getPageNo() {
	return pageNo;
}
public void setPageNo(int pageNo) {
	this.pageNo = pageNo;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
}
