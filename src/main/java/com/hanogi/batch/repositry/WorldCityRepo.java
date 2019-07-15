package com.hanogi.batch.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hanogi.batch.entity.WorldCity;

public interface WorldCityRepo extends CrudRepository<WorldCity, Integer> {
	public static final String City_Query = "Select name from world_city ORDER By name";

	@Query(value = City_Query, nativeQuery = true)
	List<String> getCities();
	
	public WorldCity findByName(String city);
	
}
