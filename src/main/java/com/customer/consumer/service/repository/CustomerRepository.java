package com.customer.consumer.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.customer.consumer.service.model.AuditLog;

@Repository
public interface CustomerRepository extends CrudRepository<AuditLog, Integer> {

}
