package com.hanogi.batch.repositry;

import org.springframework.data.repository.CrudRepository;

import com.hanogi.batch.entity.BatchRunDetails;

public interface BatchRunDetailsRepo extends CrudRepository<BatchRunDetails, Integer> {

}
