package com.briller.acess.dashboard.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.briller.acess.dto.DashboardData;




@Repository
public interface DashboardRepository extends CrudRepository<DashboardData, java.lang.Integer> {

}
