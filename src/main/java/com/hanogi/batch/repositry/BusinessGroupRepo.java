package com.hanogi.batch.repositry;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hanogi.batch.entity.business.BusinessGroup;

@Repository
public interface BusinessGroupRepo extends CrudRepository<BusinessGroup, Integer> {

	@Override
	List<BusinessGroup> findAll();

	BusinessGroup findByGroupName(String lastName);
}
