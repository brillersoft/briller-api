package com.hanogi.batch.repositry;

import org.springframework.data.repository.CrudRepository;

import com.hanogi.batch.entity.District;

public interface DistrictRepo extends CrudRepository<District, String> {
	public District findByDistrictName(String name); 

}
