package com.cricteam.service;

import java.util.List;

import com.cricteam.models.PlayerDetails;

public interface AddPlayerService {
List<PlayerDetails> addPlayers(List<PlayerDetails> playerList ,int teamId);
PlayerDetails addPlayer(PlayerDetails playerDetail);

}
