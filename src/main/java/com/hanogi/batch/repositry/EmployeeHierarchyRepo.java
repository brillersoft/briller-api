package com.hanogi.batch.repositry;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hanogi.batch.entity.EmployeeHierarchy;

@Repository
public interface EmployeeHierarchyRepo extends CrudRepository<EmployeeHierarchy, Integer> {

	@Override
	List<EmployeeHierarchy> findAll();
}
