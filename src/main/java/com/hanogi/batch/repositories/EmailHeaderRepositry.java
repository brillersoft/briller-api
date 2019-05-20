package com.hanogi.batch.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hanogi.batch.dto.EmailHeader;

@Repository
public interface EmailHeaderRepositry extends CrudRepository<EmailHeader, Integer> {

}
