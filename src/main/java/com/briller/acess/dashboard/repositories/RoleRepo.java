package com.briller.acess.dashboard.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.briller.acess.dto.Role;

public interface RoleRepo extends CrudRepository<Role,Integer> {
	Role findByRoleId(int roleId);
	
	@Query(value= "select role_name from role where role_id=?1", nativeQuery = true)
	public String getRoleName(int roleId);
	
	
}
