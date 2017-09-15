package com.cricteam.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cricteam.TeamCircleStatusEnum;
import com.cricteam.models.FindTeamRequest;
import com.cricteam.models.FullTeamDetails;
import com.cricteam.models.PlayerDetails;
import com.cricteam.models.SearchTeam;
import com.cricteam.models.TeamCircleStatus;
import com.cricteam.models.TeamDetails;
import com.cricteam.repository.TeamDetailsRepository;
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
	@Transactional
	@Override
	public FullTeamDetails getTeamViaTeamId(int userId, int teamId,int ownTeamId) {

		// TODO Auto-generated method stub
		FullTeamDetails fullTeamDetails= new FullTeamDetails();
		fullTeamDetails.setTeamdetails(teamDetailsRepository.getTeamDetailsById(userId, teamId));
		List<PlayerDetails> results = entityManager.createNativeQuery("select  * from player_details   where player_details.team_id=:teamId",PlayerDetails.class)		
				.setParameter("teamId", teamId)
				.getResultList();
		Object rating =  entityManager.createNativeQuery("SELECT avg(rating) FROM cric_team.team_rating where team_id=:teamId")		
				.setParameter("teamId", teamId).getSingleResult();
		fullTeamDetails.setTeamPlayerList(results);
		if(rating!=null)
			fullTeamDetails.setTeamRating(Double.valueOf(rating.toString()));
		Object teamCircle = null;
		try {
			teamCircle=	 entityManager.createNativeQuery("SELECT * FROM cric_team.team_circle where cric_team.team_circle.team_receiver_id=:teamId and cric_team.team_circle.team_sender_id=:ownId or cric_team.team_circle.team_receiver_id=:ownId and cric_team.team_circle.team_sender_id=:teamId ")		
					.setParameter("teamId", teamId).setParameter("ownId", ownTeamId).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(teamCircle!=null){
			fullTeamDetails.setTeamCircleStatus(createTeamCircleStatus(teamCircle,String.valueOf(teamId)));}else{
				fullTeamDetails.setTeamCircleStatus(null);
			}
		return fullTeamDetails;
	}
	public TeamCircleStatus createTeamCircleStatus( Object object,String teamId){
		String status = null;
		Object[] teamRS = (Object[])object;
		String requestId=getValueFromArray(teamRS,0);
		if(requestId.equalsIgnoreCase("")){
			return null;
		}else{
			String teamCircleStatusId=getValueFromArray(teamRS,1);
			String senderId=getValueFromArray(teamRS,3);
			String receiverId=getValueFromArray(teamRS,2);

			if(teamCircleStatusId.equalsIgnoreCase("")){
				status="Want Play";
			}else if(teamCircleStatusId.equalsIgnoreCase("0")){
				if(teamId.equalsIgnoreCase(senderId)){
					status="RECEIVED";
				}else if(teamId.equalsIgnoreCase(receiverId)){
					status=TeamCircleStatusEnum.values()[Integer.valueOf(teamCircleStatusId)].toString();
				}

			}else if(teamCircleStatusId.equalsIgnoreCase("1")){
				status="Team Circle";
			}else if(teamCircleStatusId.equalsIgnoreCase("2")){
				status="Rejected";
			}
			TeamCircleStatus info = new TeamCircleStatus();
			info.setActionStatus(status); // Notification title
			info.setSenderId( senderId);
			info.setReceiverId( receiverId);
			info.setRequestId(requestId);

			return info;}
	}
	@Override
	public TeamDetails deleteTeam(String userId, String teamId) {
		// TODOerated meth Auto-genod stub
		teamDetailsRepository.delete(teamId);
		return null;
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

	public List<SearchTeam>	getFriendList(int team_id,int userId, double lat,double lng ,int page_No,int pageSize){
		int setFirstResult=(page_No-1)*pageSize;

		List<Object> results = entityManager.createNativeQuery(	
				"select t.*, team_circle.* from (SELECT *, ( 6371 * acos( cos( radians(:lat) ) * cos( radians( team_details.team_lat ) )  * cos( radians(team_details.team_long) - radians(:longitude)) + sin(radians(:lat))"+
						"  * sin( radians(team_details.team_lat)))) AS distance FROM team_details  HAVING distance < 100 ORDER BY distance) AS t inner join cric_team.team_circle on (t.team_id=cric_team.team_circle.team_sender_id or t.team_id=cric_team.team_circle.team_receiver_id) and (cric_team.team_circle.team_receiver_id=:teamId or  cric_team.team_circle.team_sender_id=:teamId ) where cric_team.team_circle.team_circle_status_id=1 and t.user_id!=:userId")		
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

	@Override
	public TeamDetails getTeamViaTeamId(int userId, int teamId) {
		// TODO Auto-generated method stub
		return teamDetailsRepository.getTeamDetailsById(userId, teamId);
	}
	
	@Override
	public List<SearchTeam> findTeamCircle(FindTeamRequest teamRequst, String latitude, String longitude, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return getFriendList(teamRequst.getTeamId(),teamRequst.getUserId(),Double.valueOf(latitude),Double.valueOf(longitude),pageNo,pageSize);
	}

}
