package com.cricteam.service;

import java.util.List;

import com.cricteam.models.TeamDetails;

public interface TeamService {
TeamDetails saveTeam(TeamDetails teamDetails);
TeamDetails getTeamViaTeamId(String userId,String teamId);
TeamDetails deleteTeam(String userId,String teamId);
List<TeamDetails> findTeamViaLatLong(String lattiude ,String Longitude);
}
