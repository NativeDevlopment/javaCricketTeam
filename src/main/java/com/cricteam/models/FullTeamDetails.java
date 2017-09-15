package com.cricteam.models;

import java.io.Serializable;
import java.util.List;

public class FullTeamDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TeamDetails teamdetails;
	private List<PlayerDetails> teamPlayerList;
	private float teamRating;
	private int teamCircleStatus;
	public TeamDetails getTeamdetails() {
		return teamdetails;
	}
	public void setTeamdetails(TeamDetails teamdetails) {
		this.teamdetails = teamdetails;
	}
	public List<PlayerDetails> getTeamPlayerList() {
		return teamPlayerList;
	}
	public void setTeamPlayerList(List<PlayerDetails> teamPlayerList) {
		this.teamPlayerList = teamPlayerList;
	}
	public float getTeamRating() {
		return teamRating;
	}
	public void setTeamRating(float teamRating) {
		this.teamRating = teamRating;
	}
	public int getTeamCircleStatus() {
		return teamCircleStatus;
	}
	public void setTeamCircleStatus(int teamCircleStatus) {
		this.teamCircleStatus = teamCircleStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
