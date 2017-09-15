package com.cricteam.models;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="team_circle")
public class TeamCircle  implements Serializable{
	public int getTeamCircleId() {
		return teamCircleId;
	}

	public void setTeamCircleId(int teamCircleId) {
		this.teamCircleId = teamCircleId;
	}

	public TeamDetails getTeamSenderId() {
		return teamSenderId;
	}

	public void setTeamSenderId(TeamDetails teamSenderId) {
		this.teamSenderId = teamSenderId;
	}

	public TeamDetails getTeamReceiverId() {
		return teamReceiverId;
	}

	public void setTeamReceiverId(TeamDetails teamReceiverId) {
		this.teamReceiverId = teamReceiverId;
	}

	public TeamCircleStatusType getTeamCircleStatusId() {
		return teamCircleStatusId;
	}

	public void setTeamCircleStatusId(TeamCircleStatusType teamCircleStatusId) {
		this.teamCircleStatusId = teamCircleStatusId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="team_circle_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int teamCircleId;
	
	    @ManyToOne
	    @JoinColumn(referencedColumnName = "team_id")
	    TeamDetails teamSenderId;
	   
	    @JoinColumn(referencedColumnName = "team_id")
	    @ManyToOne
	    TeamDetails teamReceiverId;
	    @ManyToOne
		@JoinColumn(name="teamCircleStatusId")
		private TeamCircleStatusType teamCircleStatusId;
	  
public TeamCircle(){
	
}
}
