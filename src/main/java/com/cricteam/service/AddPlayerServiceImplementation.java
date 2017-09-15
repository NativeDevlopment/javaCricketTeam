package com.cricteam.service;

import java.util.List;

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
	@Override
	public List<PlayerDetails> addPlayers(List<PlayerDetails> playerList) {
		// TODO Auto-generated method stub
		
				List<PlayerDetails> list=addPlayerRepository.save(playerList);
				return list;
	}
	@Override
	public PlayerDetails addPlayer(PlayerDetails playerDetail) {
		// TODO Auto-generated method stub
		return addPlayerRepository.save(playerDetail);
	}

}
