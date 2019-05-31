package com.briller.acess.dashboard.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.briller.acess.dto.EmployeeCsatSummary;

@Repository
public interface EmployeeCsatSummaryRepo extends CrudRepository<EmployeeCsatSummary, Integer> {

	List<EmployeeCsatSummary> findByEmployeeId(Integer empId);

	List<EmployeeCsatSummary> findByAccountId(Integer accountId);

	String Distinct_Employee_Query = "select distinct employee_id from employee_csat_summary where account_id=?1";

	@Query(value = Distinct_Employee_Query, nativeQuery = true)
	List<Integer> findDistinctEmployees(int accountId);

// @Query( value = "select * from employee_csat_summary e where e.account_id=?1")
// List<EmployeeCsatSummary> getList(Integer accountId);

}
