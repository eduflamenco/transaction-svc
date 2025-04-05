package com.transaction.repository;

import com.transaction.entity.Entry;
import org.springframework.data.repository.CrudRepository;

public interface EntryRepo extends CrudRepository<Entry, Long> {
}
