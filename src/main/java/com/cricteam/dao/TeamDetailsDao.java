package com.cricteam.dao;

import java.util.List;

import com.cricteam.models.FindTeamRequest;
import com.cricteam.models.FullTeamDetails;
import com.cricteam.models.SearchTeam;
import com.cricteam.models.TeamDetails;

public interface TeamDetailsDao {

TeamDetails saveTeam(TeamDetails teamDetails);
FullTeamDetails getTeamViaTeamId(int userId,int teamId,int ownTeamId);
TeamDetails getTeamViaTeamId(int userId,int teamId);

TeamDetails deleteTeam(String userId,String teamId);
List<SearchTeam> findTeamViaLatLong(FindTeamRequest teamRequst, String latitude, String longitude, int pageNo,int pageSize);
List<SearchTeam> findTeamCircle(FindTeamRequest teamRequst, String latitude, String longitude, int pageNo,
		int pageSize);
}
