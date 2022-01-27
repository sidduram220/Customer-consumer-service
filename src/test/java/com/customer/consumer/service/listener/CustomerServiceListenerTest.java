package com.customer.consumer.service.listener;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.customer.consumer.service.model.Address;
import com.customer.consumer.service.model.Customer;
import com.customer.consumer.service.model.Customer.CustomerStatusEnum;
import com.customer.consumer.service.services.CustomerService;

@SpringBootTest
class CustomerServiceListenerTest {

  @Autowired
  ConsumerServiceListener consumerServiceListener;

  @MockBean
  CustomerService customerService;

  @Test
  void customerRequestListenerTest() {
    Customer customer = getCustomer();
    consumerServiceListener.customerRequestListener(customer);
    verify(customerService, times(1)).saveCustomerInfo(anyString(), anyString());

  }

  @Test
  void customerRequestListenerTestWithOutCustomer() {
    Assertions.assertThrows(NullPointerException.class,
        () -> consumerServiceListener.customerRequestListener(null));
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

