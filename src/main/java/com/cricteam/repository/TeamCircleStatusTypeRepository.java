package com.cricteam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cricteam.models.TeamCircleStatusType;

public interface TeamCircleStatusTypeRepository extends JpaRepository<TeamCircleStatusType, String> {
	@Query("select t from TeamCircleStatusType t where t.teamCircleStatusName =:action")
	TeamCircleStatusType	getTeamCircleStatus(@Param("action") String action);
}
