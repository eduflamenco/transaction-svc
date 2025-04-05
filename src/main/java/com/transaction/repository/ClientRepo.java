package com.transaction.repository;

import com.transaction.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepo extends CrudRepository<Client, Long> {
    Client findByUsername(String userName);
}
