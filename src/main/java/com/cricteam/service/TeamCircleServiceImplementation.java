package com.cricteam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cricteam.models.TeamCircle;
import com.cricteam.repository.TeamCircleRepository;
@Service("teamCircleService")
@Repository
public class TeamCircleServiceImplementation implements TeamCircleService {
@Autowired
TeamCircleRepository teamcircleRepositoty;
	@Override
	public TeamCircle sendRequest(TeamCircle teamCircle) {
		// TODO Auto-generated method stub
		return teamcircleRepositoty.save(teamCircle);
	}

	@Override
	public TeamCircle updateRequest(TeamCircle teamCircle) {
		// TODO Auto-generated method stub
		return teamcircleRepositoty.save(teamCircle);
	}

	@Override
	public TeamCircle getTeamCircleDetails(int circleRequestId) {
		// TODO Auto-generated method stub
		return teamcircleRepositoty.findOne( circleRequestId);
	}

}
