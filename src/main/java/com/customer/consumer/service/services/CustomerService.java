package com.customer.consumer.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customer.consumer.service.model.AuditLog;
import com.customer.consumer.service.model.Customer;
import com.customer.consumer.service.model.CustomerRequest;
import com.customer.consumer.service.model.ErrorLog;
import com.customer.consumer.service.repository.CustomerRepository;
import com.customer.consumer.service.repository.ErrorLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class CustomerService {

	Logger log = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ErrorLogRepository errorLogRepository;

	@Autowired
	CustomerRequestConverter customerRequestConverter;

	public void saveCustomerInfo(Customer customer) {
		ObjectMapper ob = new ObjectMapper();
		String payload = null;
		ob.registerModule(new JavaTimeModule());
		try {
			CustomerRequest cr = customerRequestConverter.covertRequestWithMasking(customer);
			payload = ob.writeValueAsString(cr);
			AuditLog cum = new AuditLog(cr.getCustomerNumber(), payload);
			customerRepository.save(cum);
			log.info("customer info saved :" + payload);
		} catch (Exception e) {
			log.error("error occured :" + e.getMessage());
			ErrorLog error = new ErrorLog(e.getClass().getSimpleName(), e.getMessage(), payload);
			errorLogRepository.save(error);
			log.info("error log info saved :" + payload);
		}
	}

}
