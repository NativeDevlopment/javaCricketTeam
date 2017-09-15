package com.cricteam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cricteam.dao.TeamDetailsDao;
import com.cricteam.models.FindTeamRequest;
import com.cricteam.models.FullTeamDetails;
import com.cricteam.models.SearchTeam;
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
	public FullTeamDetails getTeamViaTeamId(int userId, int teamId ,int ownTeamId) {
		// TODO Auto-generated method stub
		return teamDetailsDao.getTeamViaTeamId(userId, teamId,ownTeamId);
	}

	@Override
	public TeamDetails deleteTeam(String userId, String teamId) {
		// TODO Auto-generated method stub
		return teamDetailsDao.deleteTeam(userId, teamId);
	}

	

	@Override
	public List<SearchTeam> findTeamViaLatLong(FindTeamRequest teamRequst, String latitude, String longitude,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return teamDetailsDao.findTeamViaLatLong(teamRequst ,latitude, longitude,pageNo,pageSize);
	}

	@Override
	public TeamDetails getTeamViaTeamId(int userId, int teamId) {
		// TODO Auto-generated method stub
		 return teamDetailsDao.getTeamViaTeamId(userId, teamId);
	}

	@Override
	public List<SearchTeam> findTeamCircle(FindTeamRequest teamRequst, String latitude, String longitude, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return  teamDetailsDao.findTeamCircle(teamRequst ,latitude, longitude,pageNo,pageSize);
	}

}
