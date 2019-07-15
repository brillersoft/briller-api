package com.hanogi.batch.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hanogi.batch.entity.OrganizationDetails;

public interface OrganizationDetailsRepo extends CrudRepository<OrganizationDetails, Integer> {
	public OrganizationDetails findByLegalEntityId(int orgId);

	@Query(value = "select count(*) from \r\n" + "(select entity_name,entity_code from legal_entity\r\n"
			+ "group by legal_entity.entity_name,entity_code) as c\r\n"
			+ "where c.entity_name  Ilike ?1 and c.entity_code  Ilike ?2", nativeQuery = true)
	public Integer checkDuplicate(String orgName, String orgCode);
	
	@Override
	List<OrganizationDetails> findAll();
}
