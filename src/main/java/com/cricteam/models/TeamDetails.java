package com.cricteam.models;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="team_details")
public class TeamDetails {

	@Id
	@Column(name="team_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int teamId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private UserDetails userDetails;
	
	@Column(name="team_name")
	private String teamName;
	
	@Column(name="team_desc")
	private String teamDesc;
	
	@Column(name="team_lat")
	private double teamLat;
	
	@Column(name="team_long")
	private double teamLong;
	
	@Column(name="team_address")
	private String teamAddress;
	
	@Column(name="team_logo_url")
	private String teamLogoUrl;
	  @Transient
	    private double distance;
	 
	
	public double getDistance() {
			return distance;
		}

		public void setDistance(double distance) {
			this.distance = distance;
		}

	public TeamDetails(int teamId, UserDetails userDetails, String teamName, String teamDesc, double teamLat, double teamLong, String teamAddress, String teamLogoUrl ) {
		super();
		this.teamId = teamId;
		this.userDetails = userDetails;
		this.teamName = teamName;
		this.teamDesc = teamDesc;
		this.teamLat = teamLat;
		this.teamLong = teamLong;
		this.teamAddress = teamAddress;
		this.teamLogoUrl = teamLogoUrl;
	}
public TeamDetails(){
	
}

	@Override
	public String toString(){
		 return "{teamId:" + teamId + ", userDetails:" + userDetails + ", teamName:"
				+ teamName + ", teamDesc:" + teamDesc + ", teamLat:" 
				+ teamLat + ", teamAddress:" + teamAddress + ", teamLong:"+ teamLong +", teamLogoUrl:" + teamLogoUrl +", distance: "+distance+"}";
	}


	/**
	 * @return the teamId
	 */
	public int getTeamId() {
		return teamId;
	}


	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}


	/**
	 * @return the userDetails
	 */
	public UserDetails getUserDetails() {
		return userDetails;
	}


	/**
	 * @param userDetails the userDetails to set
	 */
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}


	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}


	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}


	/**
	 * @return the teamDesc
	 */
	public String getTeamDesc() {
		return teamDesc;
	}


	/**
	 * @param teamDesc the teamDesc to set
	 */
	public void setTeamDesc(String teamDesc) {
		this.teamDesc = teamDesc;
	}


	/**
	 * @return the teamLat
	 */
	public double getTeamLat() {
		return teamLat;
	}


	/**
	 * @param teamLat the teamLat to set
	 */
	public void setTeamLat(double teamLat) {
		this.teamLat = teamLat;
	}


	/**
	 * @return the teamLong
	 */
	public double getTeamLong() {
		return teamLong;
	}


	/**
	 * @param teamLong the teamLong to set
	 */
	public void setTeamLong(double teamLong) {
		this.teamLong = teamLong;
	}


	/**
	 * @return the teamAddress
	 */
	public String getTeamAddress() {
		return teamAddress;
	}


	/**
	 * @param teamAddress the teamAddress to set
	 */
	public void setTeamAddress(String teamAddress) {
		this.teamAddress = teamAddress;
	}


	/**
	 * @return the teamLogoUrl
	 */
	public String getTeamLogoUrl() {
		return teamLogoUrl;
	}


	/**
	 * @param teamLogoUrl the teamLogoUrl to set
	 */
	public void setTeamLogoUrl(String teamLogoUrl) {
		this.teamLogoUrl = teamLogoUrl;
	}
}
