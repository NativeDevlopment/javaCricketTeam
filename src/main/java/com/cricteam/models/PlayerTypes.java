package com.cricteam.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="player_type")
public class PlayerTypes {

	@Id
	@Column(name="player_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int playerTypeId;
	
	@Column(name="player_type_nm")
	private String playerTypeNm;
	
	
	
	public PlayerTypes(int playerTypeId, String playerTypeNm ) {
		super();
		this.playerTypeId = playerTypeId;
		this.playerTypeNm = playerTypeNm;
	}

	@Override
	public String toString(){
		 return "{playerTypeId:" + playerTypeId + ", playerTypeNm:" + playerTypeNm +  "}";
	}

	/**
	 * @return the playerTypeId
	 */
	public int getPlayerTypeId() {
		return playerTypeId;
	}

	/**
	 * @param playerTypeId the playerTypeId to set
	 */
	public void setPlayerTypeId(int playerTypeId) {
		this.playerTypeId = playerTypeId;
	}

	/**
	 * @return the playerTypeNm
	 */
	public String getPlayerTypeNm() {
		return playerTypeNm;
	}

	/**
	 * @param playerTypeNm the playerTypeNm to set
	 */
	public void setPlayerTypeNm(String playerTypeNm) {
		this.playerTypeNm = playerTypeNm;
	}

}
