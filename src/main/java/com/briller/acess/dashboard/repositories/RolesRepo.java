package com.briller.acess.dashboard.repositories;

import org.springframework.data.repository.CrudRepository;

import com.briller.acess.dto.Roles;

public interface RolesRepo extends CrudRepository<Roles,Integer> {


//@Query("select role_name from roles where roles.role_id=?1")
Roles findByRoleId(int roleId);
}
