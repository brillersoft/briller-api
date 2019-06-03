package com.briller.acess.dashboard.repositories;

import org.springframework.data.repository.CrudRepository;

import com.briller.acess.dto.EmployeeRoleMapping;

public interface EmployeeRoleMappingRepo extends CrudRepository<EmployeeRoleMapping,Integer> {
	
	EmployeeRoleMapping findByEmployeeId(int empId);
}
