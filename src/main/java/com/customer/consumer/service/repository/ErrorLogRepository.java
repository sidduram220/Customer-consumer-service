package com.customer.consumer.service.repository;

import org.springframework.data.repository.CrudRepository;
import com.customer.consumer.service.entity.ErrorLog;

public interface ErrorLogRepository extends CrudRepository<ErrorLog, Integer> {

}
