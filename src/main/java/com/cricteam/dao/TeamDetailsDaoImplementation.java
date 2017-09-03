package com.cricteam.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
	public TeamDetails getTeamViaTeamId(String userId, String teamId) {
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
	public List<TeamDetails> findTeamViaLatLong(String lattiude, String Longitude) {
		// TODO Auto-generated method stub
	 List<TeamDetails> list=	getDistanceList(Double.valueOf(lattiude),Double.valueOf(Longitude));
	String data=new Gson().toJsonTree(list).toString();
      Logger log = Logger.getLogger("com.cricteam");
      log.error(data);
	return list;
    //  return teamDetailsRepository.findNearbyAddress(Double.valueOf(lattiude),Double.valueOf(Longitude),25);
		



	}
	public List<TeamDetails> getDistanceList( double lat,double lng) {
		
		
		List<TeamDetails> list = entityManager.createNativeQuery("SELECT *, ( 6371 * acos( cos( radians(:lat) ) * cos( radians( team_details.team_lat ) )  * cos( radians(team_details.team_long) - radians(:longitude)) + sin(radians(:lat))"
		 +"  * sin( radians(team_details.team_lat)))) AS distance FROM team_details  HAVING distance < 100 ORDER BY distance",TeamDetails.class)		
		.setParameter("lat", lat)
		.setParameter("longitude", lng).getResultList();
		
		
		
		
		return list;
	}

}
