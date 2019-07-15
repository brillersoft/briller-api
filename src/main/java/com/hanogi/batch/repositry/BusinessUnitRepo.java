package com.hanogi.batch.repositry;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hanogi.batch.entity.business.BusinessTeam;
import com.hanogi.batch.entity.business.BusinessUnit;

@Repository
public interface BusinessUnitRepo extends CrudRepository<BusinessUnit, Integer> {

	@Override
	List<BusinessUnit> findAll();

	BusinessUnit findByBusinessUnitName(String lastName);

}
