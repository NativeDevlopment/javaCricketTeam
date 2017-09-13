package com.cricteam.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cricteam.repository.PlayerTypeRepository;
import com.cricteam.repository.TeamCircleStatusTypeRepository;
import com.cricteam.service.PredefinedService;
@Service("predefiendService")
@Repository
public class PredefinedQuery implements PredefinedService{
@Autowired 
TeamCircleStatusTypeRepository teamcircleStatusrepository;
@Autowired
PlayerTypeRepository playerTypeRepository;
	@Override
	public void saveTeamCircleStatus(List<TeamCircleStatusType> listTeamCircleStatus) {
		// TODO Auto-generated method stub
		teamcircleStatusrepository.save(listTeamCircleStatus);
		
	}
	@Override
	public int findTeamCircleStatusData() {
		// TODO Auto-generated method stub
		return teamcircleStatusrepository.findAll().size();
	}
	@Override
	public void savePlayerType(List<PlayerTypes> listPlayerType) {
		// TODO Auto-generated method stub
		playerTypeRepository.save(listPlayerType);
		
	}
	@Override
	public int findPlayerTypeData() {
		// TODO Auto-generated method stub
		return playerTypeRepository.findAll().size();
	}

}
