package com.hanogi.batch.repositry;

import org.springframework.data.repository.CrudRepository;

import com.hanogi.batch.entity.Continent;

public interface ContinentRepo extends CrudRepository<Continent, String> {
	
	Continent findByContinentName(String continent);

}
