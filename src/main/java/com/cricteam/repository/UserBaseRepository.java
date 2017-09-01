package com.cricteam.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.cricteam.models.UserDetails;


@NoRepositoryBean
public interface UserBaseRepository<T extends UserDetails> extends CrudRepository<T, String> {

}
