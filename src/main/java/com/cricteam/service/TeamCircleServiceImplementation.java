package com.cricteam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cricteam.models.TeamCircle;
import com.cricteam.repository.TeamCircleRepository;
@Service("teamCircleService")
@Repository
public class TeamCircleServiceImplementation implements TeamCircleService {
@Autowired
TeamCircleRepository teamcircleRepositoty;
@Transactional
	@Override
	public TeamCircle sendRequest(TeamCircle teamCircle) {
		// TODO Auto-generated method stub
		return teamcircleRepositoty.save(teamCircle);
	}
@Transactional
	@Override
	public TeamCircle updateRequest(TeamCircle teamCircle) {
		// TODO Auto-generated method stub
		return teamcircleRepositoty.save(teamCircle);
	}
@Transactional
	@Override
	public TeamCircle getTeamCircleDetails(int circleRequestId) {
		// TODO Auto-generated method stub
		return teamcircleRepositoty.findOne( circleRequestId);
	}
@Transactional
	@Override
	public TeamCircle getTeamCircleDetails(int senderId, int receiverId) {
		// TODO Auto-generated method stub
		return teamcircleRepositoty.getTeamCircle(senderId, receiverId);
	}
@Override
public TeamCircle deleteRequest(TeamCircle teamCircle) {
	teamcircleRepositoty.delete(teamCircle);
	// TODO Auto-generated method stub
	return null;
}

}
