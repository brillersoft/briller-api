package com.briller.acess.dashboard.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.briller.acess.dto.EmployeeEmailMapping;

@Repository
public interface EmployeeEmailMappingRepositry extends CrudRepository<EmployeeEmailMapping, String> {
	public List<EmployeeEmailMapping> findByAnalyseTone(String analyseTone);
	
//	@Query("select email_id from employee_emailid_mapping where employee_id=?1")
//	
	@Query(value = "select email_id from employee_emailid_mapping where employee_id=?1", 
			  nativeQuery = true)
	public String getEmailId(int  empId);
	EmployeeEmailMapping findByEmployeeId(String emailId);
		
}
