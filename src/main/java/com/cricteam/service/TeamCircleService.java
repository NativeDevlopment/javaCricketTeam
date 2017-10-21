package com.cricteam.service;

import com.cricteam.models.TeamCircle;

public interface TeamCircleService {

	TeamCircle	sendRequest (TeamCircle teamCircle);
	TeamCircle	deleteRequest (TeamCircle teamCircle);

	TeamCircle updateRequest(TeamCircle teamCircle);
	TeamCircle getTeamCircleDetails(int circleRequestId);
	TeamCircle getTeamCircleDetails(int senderId,int receiverId);


	
}
