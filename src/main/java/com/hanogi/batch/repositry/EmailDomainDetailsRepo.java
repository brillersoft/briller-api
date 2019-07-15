package com.hanogi.batch.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hanogi.batch.entity.EmailDomainDetails;

public interface EmailDomainDetailsRepo extends CrudRepository<EmailDomainDetails, Integer> {

	public static final String All_Domain_Query = "Select email_domain_name from email_domain_details;";

	@Query(value = All_Domain_Query, nativeQuery = true)
	List<String> getAllDomainsName();

}
