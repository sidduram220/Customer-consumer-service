package com.customer.consumer.service.converter;

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
class CustomerRequestConverterTest {

  @Autowired
  private CustomerRequestConverter customerRequestConverter;

  @Test
  void convertRequestWithMaskingTest() {
    CustomerRequest customerRequest = customerRequest();
    assertEquals("012345****", customerRequest.getCustomerNumber());
    assertEquals("****il@gmail.com", customerRequest.getEmail());

  }

  private CustomerRequest customerRequest() {
    Customer customer = new Customer();
    customer.setAddress(new Address());
    customer.setCustomerNumber("0123456789");
    customer.setEmail("mymail@gmail.com");
    customer.setBirthDate(new Date());
    customer.setCustomerStatus(CustomerStatusEnum.O);
    CustomerRequest customerRequest = customerRequestConverter.convertRequestWithMasking(customer);
    return customerRequest;
  }

}
