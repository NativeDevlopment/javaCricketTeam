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
