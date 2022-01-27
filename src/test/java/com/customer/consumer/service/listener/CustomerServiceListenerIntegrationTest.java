package com.customer.consumer.service.listener;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.customer.consumer.service.entity.AuditLog;
import com.customer.consumer.service.model.Address;
import com.customer.consumer.service.model.Customer;
import com.customer.consumer.service.model.Customer.CustomerStatusEnum;
import com.customer.consumer.service.repository.CustomerRepository;

@SpringBootTest
public class CustomerServiceListenerIntegrationTest {

  @Autowired
  ConsumerServiceListener consumerServiceListener;

  @MockBean
  CustomerRepository customerRepository;

  @Test
  void customerRequestListenerTest() {
    Customer customer = getCustomer();
    consumerServiceListener.customerRequestListener(customer);
    verify(customerRepository, times(1)).save(Mockito.any(AuditLog.class));

  }

  private Customer getCustomer() {
    Customer customer = new Customer();
    customer.setAddress(new Address());
    customer.setCustomerNumber("0123456789");
    customer.setEmail("mymail@gmail.com");
    customer.setBirthDate(new Date());
    customer.setCustomerStatus(CustomerStatusEnum.O);
    return customer;
  }
}

