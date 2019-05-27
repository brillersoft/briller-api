package com.hanogi.batch.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hanogi.batch.dto.IndividualTone;

@Repository
public interface IndividualToneRepositry extends CrudRepository<IndividualTone, Integer> {

}
