package com.hanogi.batch.repositry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hanogi.batch.entity.Client;

@Repository
public interface ClientRepositry extends CrudRepository<Client, Integer> {

}
