package com.briller.acess.dashboard.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.briller.acess.dto.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
	@Override
	List<Account> findAll();
}
