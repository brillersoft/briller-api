package com.hanogi.batch.repositry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hanogi.batch.entity.Employee;

@Repository
public interface EmployeeRepositry extends CrudRepository<Employee, Integer> {

}
