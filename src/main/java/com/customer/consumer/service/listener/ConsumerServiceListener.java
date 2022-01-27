package com.customer.consumer.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import com.customer.consumer.service.converter.CustomerRequestConverter;
import com.customer.consumer.service.model.Customer;
import com.customer.consumer.service.model.CustomerRequest;
import com.customer.consumer.service.services.CustomerService;
import com.customer.consumer.service.util.ObjectMapperUtil;

@Service
public class ConsumerServiceListener {

  Logger log = LoggerFactory.getLogger(ConsumerServiceListener.class);

  private static final String MYTOPIC = "Mytopic1";
  private static final String GROUPID = "siddu1";
  private static final String containerFactory = "concurrentKafkaListenerContainerFactory";

  @Autowired
  CustomerService customerService;

  @Autowired
  CustomerRequestConverter customerRequestConverter;

  @KafkaListener(groupId = GROUPID, topics = MYTOPIC, containerFactory = containerFactory)
  public void customerRequestListener(@Payload Customer customer) {
    CustomerRequest customerRequest = customerRequestConverter.convertRequestWithMasking(customer);
    String customerJsonRequest = ObjectMapperUtil.asJsonString(customerRequest);
    log.info("Customer request received : {}", customerJsonRequest);
    customerService.saveCustomerInfo(customerRequest.getCustomerNumber(), customerJsonRequest);
  }

}
