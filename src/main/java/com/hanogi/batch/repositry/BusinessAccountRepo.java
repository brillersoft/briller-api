package com.hanogi.batch.repositry;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hanogi.batch.entity.business.BusinessAccount;

@Repository
public interface BusinessAccountRepo extends CrudRepository<BusinessAccount, Integer> {

	@Override
	public List<BusinessAccount> findAll();

	public BusinessAccount findByAccountName(String lastName);

}
