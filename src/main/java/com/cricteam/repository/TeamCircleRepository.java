package com.cricteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cricteam.models.TeamCircle;

public interface TeamCircleRepository extends JpaRepository<TeamCircle, Integer> {
	@Query("select t from TeamCircle t where t.teamSenderId.teamId =:senderId and t.teamReceiverId.teamId =:receiverId")
	TeamCircle	getTeamCircle(@Param("senderId") int  senderId,@Param("receiverId") int receiverId);

}
