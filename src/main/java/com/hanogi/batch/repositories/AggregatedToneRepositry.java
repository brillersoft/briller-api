package com.hanogi.batch.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hanogi.batch.dto.AggregatedTone;

@Repository
public interface AggregatedToneRepositry extends CrudRepository<AggregatedTone, java.lang.Integer> {

}
