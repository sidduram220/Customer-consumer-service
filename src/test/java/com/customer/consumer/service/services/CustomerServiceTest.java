package com.customer.consumer.service.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.customer.consumer.service.model.Address;
import com.customer.consumer.service.model.AuditLog;
import com.customer.consumer.service.model.Customer;
import com.customer.consumer.service.model.Customer.CustomerStatusEnum;
import com.customer.consumer.service.model.ErrorLog;
import com.customer.consumer.service.repository.CustomerRepository;
import com.customer.consumer.service.repository.ErrorLogRepository;

@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	CustomerService customerService;

	@MockBean
	CustomerRepository customerRepository;

	@MockBean
	ErrorLogRepository errorLogRepository;

	@Test
	void saveCustomerInfoTest() {
		Customer c = new Customer();
		c.setAddress(new Address());
		c.setCustomerNumber("0123456789");
		c.setEmail("mymail@gmail.com");
		c.setBirthDate(new Date());
		c.setCustomerStatus(CustomerStatusEnum.OPEN);
		customerService.saveCustomerInfo(c);
		verify(customerRepository, times(1)).save(Mockito.any(AuditLog.class));

	}

	@Test
	void saveErrorInfoWithExceptionTest() {
		Customer c = new Customer();
		c.setAddress(new Address());
		c.setEmail("mymail@gmail.com");
		c.setBirthDate(new Date());
		c.setCustomerStatus(CustomerStatusEnum.OPEN);
		customerService.saveCustomerInfo(c);
		verify(errorLogRepository, times(1)).save(Mockito.any(ErrorLog.class));

	}

}
