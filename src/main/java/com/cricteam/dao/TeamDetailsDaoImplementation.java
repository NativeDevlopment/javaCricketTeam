package com.cricteam.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.ColumnResult;
import javax.persistence.EntityManager;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cricteam.TeamCircleStatusEnum;
import com.cricteam.models.FindTeamRequest;
import com.cricteam.models.SearchTeam;
import com.cricteam.models.TeamDetails;
import com.cricteam.repository.TeamDetailsRepository;
import com.google.gson.Gson;
@Service("teamDetailsDao")
@Repository
public class TeamDetailsDaoImplementation implements TeamDetailsDao {
@Autowired
TeamDetailsRepository<TeamDetails> teamDetailsRepository;
@PersistenceContext
protected EntityManager entityManager;
	@Override
	public TeamDetails saveTeam(TeamDetails teamDetails) {
		// TODO Auto-generated method stub
		return teamDetailsRepository.save(teamDetails);
	}

	@Override
	public TeamDetails getTeamViaTeamId(int userId, int teamId) {
		// TODO Auto-generated method stub
		return teamDetailsRepository.getTeamDetailsById(userId, teamId);
	}

	@Override
	public TeamDetails deleteTeam(String userId, String teamId) {
		// TODOerated meth Auto-genod stub
				teamDetailsRepository.delete(teamId);
				return null;
	}

	@Override
	public List<SearchTeam> findTeamViaLatLong(String lattiude, String Longitude,int page_No,int pageSize) {
		// TODO Auto-generated method stub
	
	//return getDistanceList(Double.valueOf(lattiude),Double.valueOf(Longitude),page_No,pageSize);
 return null;
		// teamDetailsRepository.findNearbyAddress(Double.valueOf(lattiude),Double.valueOf(Longitude),25);
	}
	private String getValueFromArray(Object[] teamRS, int i) {
		return (teamRS[i]!=null)?teamRS[i].toString():"";
	}
	public List<SearchTeam> createTeamObject(List<Object> queryResp){
		
		List<SearchTeam> searchTeamList = new ArrayList<SearchTeam>();
		
		for (Iterator<Object> it = queryResp.iterator(); it.hasNext();) {
			Object[] teamRS = (Object[]) it.next();
/*			[1,"new delhi","crazy 11",28.5275,null,77.0689,"team1",1,0.0,49.40307284026041]
*/			SearchTeam searchTeam = new SearchTeam();
			
			searchTeam.setTeamId(getValueFromArray(teamRS,0));
			searchTeam.setTeamAddress(getValueFromArray(teamRS,1));
			searchTeam.setTeamDesc(getValueFromArray(teamRS, 2));
			searchTeam.setTeamLat(getValueFromArray(teamRS, 3));
			searchTeam.setTeamLogoUrl(getValueFromArray(teamRS, 4));
			searchTeam.setTeamLong(getValueFromArray(teamRS, 5));
			searchTeam.setTeamName(getValueFromArray(teamRS, 6));
			searchTeam.setUserId(getValueFromArray(teamRS, 7));
			
			searchTeam.setDistance(getValueFromArray(teamRS, 8));
			searchTeam.setTeamRequestId(getValueFromArray(teamRS, 9));
			searchTeam.setTeamReceiverId(getValueFromArray(teamRS, 11));
			searchTeam.setTeamSenderId(getValueFromArray(teamRS, 12));

			if(getValueFromArray(teamRS, 10).equalsIgnoreCase("")){
				searchTeam.setTeamCircleStatus("Want Play");
			}else{
				if(Integer.valueOf(getValueFromArray(teamRS, 10))==0){
				if(searchTeam.getTeamId().equals(searchTeam.getTeamSenderId()))
				searchTeam.setTeamCircleStatus(TeamCircleStatusEnum.values()[Integer.valueOf(getValueFromArray(teamRS, 10))].toString());
				else{
					searchTeam.setTeamCircleStatus("RECEIVED");
					}
				}else{
					searchTeam.setTeamCircleStatus(TeamCircleStatusEnum.values()[Integer.valueOf(getValueFromArray(teamRS, 10))].toString());

				}
			}
			
		//	searchTeam.set(getValueFromArray(teamRS,2));
				searchTeamList.add(searchTeam);
			
			}
return searchTeamList;
	}
	public List<SearchTeam> getDistanceList(int team_id,int userId, double lat,double lng ,int page_No,int pageSize) {
				
	int setFirstResult=(page_No-1)*pageSize;
		List<Object> results = entityManager.createNativeQuery("select t.*, team_circle.* from (SELECT *, ( 6371 * acos( cos( radians(:lat) ) * cos( radians( team_details.team_lat ) )  * cos( radians(team_details.team_long) - radians(:longitude)) + sin(radians(:lat))"
		 +"  * sin( radians(team_details.team_lat)))) AS distance FROM team_details  HAVING distance < 100 ORDER BY distance) AS t LEFT JOIN cric_team.team_circle  on   cric_team.team_circle.team_sender_id=t.team_id and cric_team.team_circle.team_sender_id=:teamId  where t.user_id!=:userId")		
		.setParameter("lat", lat).setParameter("userId", userId).setParameter("teamId", team_id)
		.setParameter("longitude", lng).setFirstResult(setFirstResult).setMaxResults(pageSize).getResultList();
		List<SearchTeam> searchTeams=createTeamObject(results);
		
		
		
		
		return searchTeams;
	}

	@Override
	public List<SearchTeam> findTeamViaLatLong(FindTeamRequest teamRequst, String latitude, String longitude,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return getDistanceList(teamRequst.getTeamId(),teamRequst.getUserId(),Double.valueOf(latitude),Double.valueOf(longitude),pageNo,pageSize);
	}

}
