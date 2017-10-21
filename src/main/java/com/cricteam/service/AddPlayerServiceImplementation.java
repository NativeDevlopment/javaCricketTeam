package com.cricteam.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cricteam.models.PlayerDetails;
import com.cricteam.repository.AddPlayerRepository;
@Service("addPlayerService")
@Repository
public class AddPlayerServiceImplementation implements AddPlayerService {
@Autowired
AddPlayerRepository addPlayerRepository;
@Autowired
EntityManager entityManager;
	@Override
	public List<PlayerDetails> addPlayers(List<PlayerDetails> playerList ,int teamId) {
		// TODO Auto-generated method stub
		
				List<PlayerDetails> list=addPlayerRepository.save(playerList);
				
				
				return getPlayerList(teamId);
	}
	@Override
	public PlayerDetails addPlayer(PlayerDetails playerDetail) {
		// TODO Auto-generated method stub
		return addPlayerRepository.save(playerDetail);
	}
	List<PlayerDetails> getPlayerList( int teamId){
	List<PlayerDetails> results = entityManager.createNativeQuery("select  * from player_details   where player_details.team_id=:teamId",PlayerDetails.class)		
			.setParameter("teamId", teamId)
			.getResultList();
	return results;
}
}
