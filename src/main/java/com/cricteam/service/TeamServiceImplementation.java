package com.cricteam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cricteam.dao.TeamDetailsDao;
import com.cricteam.models.TeamDetails;
@Service("teamService")
@Repository
public class TeamServiceImplementation implements TeamService {
@Autowired
TeamDetailsDao teamDetailsDao;
	@Override
	public TeamDetails saveTeam(TeamDetails teamDetails) {
		// TODO Auto-generated method stub
       return teamDetailsDao.saveTeam(teamDetails);	}

	@Override
	public TeamDetails getTeamViaTeamId(String userId, String teamId) {
		// TODO Auto-generated method stub
		return teamDetailsDao.getTeamViaTeamId(userId, teamId);
	}

	@Override
	public TeamDetails deleteTeam(String userId, String teamId) {
		// TODO Auto-generated method stub
		return teamDetailsDao.deleteTeam(userId, teamId);
	}

	@Override
	public List<TeamDetails> findTeamViaLatLong(String lattiude, String Longitude) {
		// TODO Auto-generated method stub
		return teamDetailsDao.findTeamViaLatLong(lattiude, Longitude);
	}

}
