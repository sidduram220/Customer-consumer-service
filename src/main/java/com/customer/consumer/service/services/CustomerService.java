package com.customer.consumer.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.consumer.service.model.AuditLog;
import com.customer.consumer.service.model.ErrorLog;
import com.customer.consumer.service.repository.CustomerRepository;
import com.customer.consumer.service.repository.ErrorLogRepository;

@Service
public class CustomerService {

	Logger log = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ErrorLogRepository errorLogRepository;

	public void saveCustomerInfo(String customerNumber, String payload) {
		AuditLog auditLog = new AuditLog(customerNumber, payload);
		try {
			customerRepository.save(auditLog);
			log.info("customer info saved :" + payload);
		} catch (Exception e) {
			log.error("error occured :" + e.getMessage());
			ErrorLog error = new ErrorLog(e.getClass().getSimpleName(), e.getMessage(), payload);
			errorLogRepository.save(error);
			log.info("error log info saved :" + payload);
		}
	}

}
