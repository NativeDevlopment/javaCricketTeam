package com.cricteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cricteam.models.PlayerDetails;

public interface AddPlayerRepository extends JpaRepository<PlayerDetails, String> {

}
