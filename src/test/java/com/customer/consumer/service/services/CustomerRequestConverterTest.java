package com.customer.consumer.service.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.customer.consumer.service.model.Address;
import com.customer.consumer.service.model.Customer;
import com.customer.consumer.service.model.Customer.CustomerStatusEnum;
import com.customer.consumer.service.model.CustomerRequest;

@SpringBootTest
public class CustomerRequestConverterTest {

	@Autowired
	CustomerRequestConverter customerRequestConverter;

	@Test
	void covertRequestWithMaskingTest() throws Exception {
		CustomerRequest cr = customerRequest();
		assertEquals("012345****", cr.getCustomerNumber());
		assertEquals("****il@gmail.com", cr.getEmail());

	}

	private CustomerRequest customerRequest() throws Exception {
		Customer c = new Customer();
		c.setAddress(new Address());
		c.setCustomerNumber("0123456789");
		c.setEmail("mymail@gmail.com");
		c.setBirthDate(new Date());
		c.setCustomerStatus(CustomerStatusEnum.OPEN);
		CustomerRequest cr = customerRequestConverter.covertRequestWithMasking(c);
		return cr;
	}

}
