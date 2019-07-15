package com.hanogi.batch.repositry;

import org.springframework.data.repository.CrudRepository;

import com.hanogi.batch.entity.GeoLocation;

public interface GeoLocationRepo extends CrudRepository<GeoLocation,Integer> {

}
