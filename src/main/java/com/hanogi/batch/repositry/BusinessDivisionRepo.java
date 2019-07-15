package com.hanogi.batch.repositry;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hanogi.batch.entity.business.BusinessDivision;

@Repository
public interface BusinessDivisionRepo extends CrudRepository<BusinessDivision, Integer> {

	@Override
	List<BusinessDivision> findAll();

	BusinessDivision findByDivisionName(String name);

}
