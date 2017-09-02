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

	
	
}
