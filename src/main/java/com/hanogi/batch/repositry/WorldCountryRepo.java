package com.hanogi.batch.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hanogi.batch.entity.WorldCountry;

public interface WorldCountryRepo extends CrudRepository<WorldCountry, Integer> {

	public static final String Country_Query = " select country_name from world_country ORDER By country_name";

	@Query(value = Country_Query, nativeQuery = true)
	List<String> getCountryNames();

	@Override
	List<WorldCountry> findAll();

	WorldCountry findByCountryName(String country);
}
