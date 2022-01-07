package com.customer.consumer.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;
import com.customer.consumer.service.model.Customer;
import com.customer.consumer.service.services.CustomerService;
import com.customer.consumer.service.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ConsumerServiceListener {

	Logger log = LoggerFactory.getLogger(ConsumerServiceListener.class);

	private static final String MYTOPIC = "Mytopic1";
	private static final String SIDDU1 = "siddu1";
	private static final String TRANSACTIONID = "Transaction-Id";
	private static final String ACTIVITYID = "Activity-Id";

	@Autowired
	CustomerService customerService;

	@KafkaListener(groupId = SIDDU1, topics = MYTOPIC, containerFactory = "concurrentKafkaListenerContainerFactory")
	public void name(@Payload Customer customer, @Header(TRANSACTIONID) Integer transactionId,
			@Header(ACTIVITYID) Integer ActivityId) throws JsonProcessingException {
		String jsonString = ObjectMapperUtil.asJsonString(customer);
		log.info("message received : " + jsonString);
		customerService.saveCustomerInfo(customer);
	}

}
