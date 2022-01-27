package com.customer.consumer.service.converter;

import com.customer.consumer.service.model.Customer;
import com.customer.consumer.service.model.CustomerRequest;

public interface CustomerRequestConverter {

  public CustomerRequest convertRequestWithMasking(Customer customer);
}
