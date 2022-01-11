package com.customer.consumer.service.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.customer.consumer.service.model.AuditLog;
import com.customer.consumer.service.model.CustomerAddress;
import com.customer.consumer.service.model.CustomerRequest;
import com.customer.consumer.service.model.ErrorLog;
import com.customer.consumer.service.repository.CustomerRepository;
import com.customer.consumer.service.repository.ErrorLogRepository;
import com.customer.consumer.service.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
class CustomerServiceTest {

  @Autowired
  CustomerService customerService;

  @MockBean
  CustomerRepository customerRepository;

  @Autowired
  CustomerRepository customerRepo;

  @MockBean
  ErrorLogRepository errorLogRepository;

  @Test
  void saveCustomerInfoTest() throws JsonProcessingException {
    CustomerRequest customerRequest = getCustomerRequest();
    String payload = ObjectMapperUtil.asJsonString(customerRequest);
    customerService.saveCustomerInfo(customerRequest.getCustomerNumber(), payload);
    verify(customerRepository, times(1)).save(Mockito.any(AuditLog.class));

  }

  @Test
  void failSaveCustomerInfoWithExceptionTest() {
    when(customerRepo.save(Mockito.any())).thenThrow(RuntimeException.class);
    customerService.saveCustomerInfo(null, null);
    verify(errorLogRepository, times(1)).save(Mockito.any(ErrorLog.class));

  }

  private CustomerRequest getCustomerRequest() {
    CustomerRequest c = new CustomerRequest();
    c.setAddress(new CustomerAddress());
    c.setCustomerNumber("0123456789");
    c.setEmail("mymail@gmail.com");
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    String strDate = formatter.format(date);
    c.setBirthDate(strDate);
    c.setCustomerStatus("Open");
    return c;
  }

}
