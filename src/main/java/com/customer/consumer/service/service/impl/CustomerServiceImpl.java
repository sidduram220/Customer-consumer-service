package com.customer.consumer.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customer.consumer.service.entity.AuditLog;
import com.customer.consumer.service.repository.CustomerRepository;
import com.customer.consumer.service.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

  private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public void saveCustomerInfo(String customerNumber, String payload) {
    AuditLog auditLog = new AuditLog(customerNumber, payload);
    customerRepository.save(auditLog);
    log.info("customer info saved : {}", payload);
  }

}
