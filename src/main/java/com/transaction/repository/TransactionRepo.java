package com.transaction.repository;

import com.transaction.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TransactionRepo extends CrudRepository<Transaction, UUID> {
}
