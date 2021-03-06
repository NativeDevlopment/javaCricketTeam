package com.cricteam.service;

import java.util.List;

import com.cricteam.models.PlayerTypes;
import com.cricteam.models.TeamCircleStatusType;

public interface PredefinedService {
 void saveTeamCircleStatus (List<TeamCircleStatusType> listTeamCircleStatus);
 void savePlayerType (List<PlayerTypes> listTeamCircleStatus);
 TeamCircleStatusType findCircleStatus(String action);
 int  findTeamCircleStatusData();
 int  findPlayerTypeData();

}
