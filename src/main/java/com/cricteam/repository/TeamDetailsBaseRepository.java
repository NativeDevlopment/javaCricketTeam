package com.cricteam.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.cricteam.models.TeamDetails;
import com.cricteam.models.UserDetails;


@NoRepositoryBean
public interface TeamDetailsBaseRepository<T extends TeamDetails> extends CrudRepository<T, String> {

}
