package com.hanogi.batch.repositry;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hanogi.batch.entity.business.BusinessTeam;

public interface BusinessTeamRepo extends CrudRepository<BusinessTeam, Integer> {

	@Override
	List<BusinessTeam> findAll();

	BusinessTeam findByTeamName(String lastName);
}
