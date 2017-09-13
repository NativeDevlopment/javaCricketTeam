package com.cricteam.service;

import java.util.List;

import com.cricteam.models.FindTeamRequest;
import com.cricteam.models.SearchTeam;
import com.cricteam.models.TeamDetails;

public interface TeamService {
TeamDetails saveTeam(TeamDetails teamDetails);
TeamDetails getTeamViaTeamId(int userId,int teamId);
TeamDetails deleteTeam(String userId,String teamId);
List<SearchTeam> findTeamViaLatLong(String lattiude ,String Longitude,int page_No,int pageSize);
List<SearchTeam> findTeamViaLatLong(FindTeamRequest teamRequst, String latitude, String longitude, int pageNo, int pageSize);
}

