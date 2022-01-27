package com.customer.consumer.service.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.customer.consumer.service.entity.AuditLog;
import com.customer.consumer.service.model.Address;
import com.customer.consumer.service.model.CustomerRequest;
import com.customer.consumer.service.repository.CustomerRepository;
import com.customer.consumer.service.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
class CustomerServiceTest {

  @Autowired
  CustomerService customerService;

  @MockBean
  CustomerRepository customerRepository;

  @Test
  void saveCustomerInfoTest() throws JsonProcessingException {
    CustomerRequest customerRequest = getCustomerRequest();
    String payload = ObjectMapperUtil.asJsonString(customerRequest);
    customerService.saveCustomerInfo(customerRequest.getCustomerNumber(), payload);
    verify(customerRepository, times(1)).save(Mockito.any(AuditLog.class));

  }

  private CustomerRequest getCustomerRequest() {
    CustomerRequest customerRequest = new CustomerRequest();
    customerRequest.setAddress(new Address());
    customerRequest.setCustomerNumber("0123456789");
    customerRequest.setEmail("mymail@gmail.com");
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    String strDate = formatter.format(date);
    customerRequest.setBirthDate(strDate);
    customerRequest.setCustomerStatus("Open");
    return customerRequest;
  }
}

