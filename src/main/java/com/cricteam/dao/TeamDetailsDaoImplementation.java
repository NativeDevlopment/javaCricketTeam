package com.cricteam.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cricteam.models.TeamDetails;
import com.cricteam.repository.TeamDetailsRepository;
@Service("teamDetailsDao")
@Repository
public class TeamDetailsDaoImplementation implements TeamDetailsDao {
@Autowired
TeamDetailsRepository<TeamDetails> teamDetailsRepository;
	@Override
	public TeamDetails saveTeam(TeamDetails teamDetails) {
		// TODO Auto-generated method stub
		return teamDetailsRepository.save(teamDetails);
	}

	@Override
	public TeamDetails getTeamViaTeamId(String userId, String teamId) {
		// TODO Auto-generated method stub
		return teamDetailsRepository.getTeamDetailsById(userId, teamId);
	}

	@Override
	public TeamDetails deleteTeam(String userId, String teamId) {
		// TODO Auto-generated method stub
				teamDetailsRepository.delete(teamId);
				return null;
	}

	@Override
	public List<TeamDetails> findTeamViaLatLong(String lattiude, String Longitude) {
		// TODO Auto-generated method stub
		return teamDetailsRepository.findTeamViaLatLong(lattiude, Longitude);
	}

}
