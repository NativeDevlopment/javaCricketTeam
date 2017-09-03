package com.cricteam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cricteam.models.TeamDetails;

public interface TeamDetailsRepository<T> extends TeamDetailsBaseRepository<TeamDetails>,JpaRepository<TeamDetails, String> {

	@Query("select t from TeamDetails t where t.userDetails.userId =:mid  and t.teamId=:tid")
	TeamDetails getTeamDetailsById(@Param("mid") String mid,@Param ("tid") String tid);
	@Query("select t from TeamDetails t where t.teamLat =:teamLat and t.teamLong=:teamLong")
	List<TeamDetails> findTeamViaLatLong(@Param("teamLat") String teamLat ,@Param("teamLong") String teamLong);
	@Query(value = "SELECT  s FROM TeamDetails s WHERE 1 = 1 AND " +
	        "(pow(69.1 * (s.teamLat - ?1), 2) + pow(69.1 * (?2 - s.teamLong) * cos(s.teamLat / 57.3), 2)) < pow(?3,2)" 

	)
	List<TeamDetails> findNearbyAddress(double lat, double lng, double radius); 
	
	
}
