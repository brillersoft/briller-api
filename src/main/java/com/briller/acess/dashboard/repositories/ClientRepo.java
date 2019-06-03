package com.briller.acess.dashboard.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.briller.acess.dto.Client;


@Repository
public interface ClientRepo extends CrudRepository<Client,Integer> {

Client findByClientId(int clientId);
}
