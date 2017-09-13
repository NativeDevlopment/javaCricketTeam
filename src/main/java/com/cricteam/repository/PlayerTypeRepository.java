package com.cricteam.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cricteam.models.PlayerTypes;

public interface PlayerTypeRepository extends JpaRepository<PlayerTypes, String> {

}
