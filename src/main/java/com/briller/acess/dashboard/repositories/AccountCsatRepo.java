package com.briller.acess.dashboard.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.briller.acess.dto.AccountCsatSummary;
@Repository
public interface AccountCsatRepo extends CrudRepository<AccountCsatSummary,Integer> {
	
	AccountCsatSummary findByAccountId(int accountId);

}
