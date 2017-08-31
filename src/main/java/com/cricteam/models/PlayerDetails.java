package com.cricteam.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="player_details")
public class PlayerDetails {

	@Id
	@Column(name="player_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int playerId;
	
	@ManyToOne
	@JoinColumn(name="teamId")
	private TeamDetails teamDetails;
	
	@Column(name="player_name")
	private String playerName;
	
	@Column(name="player_mobile")
	private String playerMobile;
	
	@Column(name="player_image_url")
	private String playerImageUrl;
	
	@ManyToOne
	@JoinColumn(name="playerTypeId")
	private PlayerTypes playerTypes;
	
	@Column(name="leader_ship")
	private String leaderShip;
	
	public PlayerDetails(int playerId, TeamDetails teamDetails, PlayerTypes playerTypes, String playerName, String playerMobile, String playerImageUrl) {
		super();
		this.playerId = playerId;
		this.teamDetails = teamDetails;
		this.playerName = playerName;
		this.playerTypes = playerTypes;
	}


	@Override
	public String toString(){
		 return "{playerId:" + playerId + ", teamDetails:"
				+ teamDetails + ", playerName:" + playerName + ", playerMobile:" 
				+ playerMobile + ", playerImageUrl:" + playerImageUrl + ", playerTypes:" + playerTypes +"}";
	}


	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}


	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the teamDetails
	 */
	public TeamDetails getTeamDetails() {
		return teamDetails;
	}


	/**
	 * @param teamDetails the teamDetails to set
	 */
	public void setTeamDetails(TeamDetails teamDetails) {
		this.teamDetails = teamDetails;
	}


	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}


	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	/**
	 * @return the playerMobile
	 */
	public String getPlayerMobile() {
		return playerMobile;
	}


	/**
	 * @param playerMobile the playerMobile to set
	 */
	public void setPlayerMobile(String playerMobile) {
		this.playerMobile = playerMobile;
	}


	/**
	 * @return the playerImageUrl
	 */
	public String getPlayerImageUrl() {
		return playerImageUrl;
	}


	/**
	 * @param playerImageUrl the playerImageUrl to set
	 */
	public void setPlayerImageUrl(String playerImageUrl) {
		this.playerImageUrl = playerImageUrl;
	}


	/**
	 * @return the playerTypes
	 */
	public PlayerTypes getPlayerTypes() {
		return playerTypes;
	}


	/**
	 * @param playerTypes the playerTypes to set
	 */
	public void setPlayerTypes(PlayerTypes playerTypes) {
		this.playerTypes = playerTypes;
	}
}
