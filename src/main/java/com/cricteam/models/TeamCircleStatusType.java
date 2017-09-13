package com.cricteam.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="team_circle_status_type")
public class TeamCircleStatusType {

	@Id
	@Column(name="team_circle_status_id")
	private int teamCircleStatusId;
	
	@Column(name="team_circle_status_nm")
	private String teamCircleStatusName;
	
	public TeamCircleStatusType(){
		
	}
	
	public TeamCircleStatusType(int teamCircleStatusId, String teamCircleStatusName ) {
		super();
		this.teamCircleStatusId = teamCircleStatusId;
		this.teamCircleStatusName = teamCircleStatusName;
	}

	@Override
	public String toString(){
		 return "{teamCircleStatusId:" + teamCircleStatusId + ", teamCircleStatusName:" + teamCircleStatusName +  "}";
	}

	public int getTeamCircleStatusId() {
		return teamCircleStatusId;
	}

	public void setTeamCircleStatusId(int teamCircleStatusId) {
		this.teamCircleStatusId = teamCircleStatusId;
	}

	public String getTeamCircleStatusName() {
		return teamCircleStatusName;
	}

	public void setTeamCircleStatusName(String teamCircleStatusName) {
		this.teamCircleStatusName = teamCircleStatusName;
	}

	

}
