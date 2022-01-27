package com.customer.consumer.service.repository;

import org.springframework.data.repository.CrudRepository;
import com.customer.consumer.service.entity.AuditLog;

public interface CustomerRepository extends CrudRepository<AuditLog, Integer> {

}
