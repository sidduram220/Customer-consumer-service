package com.customer.consumer.service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.customer.consumer.service.entity.ErrorLog;
import com.customer.consumer.service.repository.ErrorLogRepository;

@Component
@Aspect
public class CustomerAspect {

  Logger log = LoggerFactory.getLogger(CustomerAspect.class);

  @Autowired
  ErrorLogRepository errorLogRepository;

  @AfterThrowing(
      value = "execution(* com.customer.consumer.service.services.CustomerService.saveCustomerInfo(..)) && args(customerNumber,payload))",
      throwing = "exception")
  public void afterThrowingException(JoinPoint joinPoint, RuntimeException exception,
      String customerNumber, String payload) {
    log.error("error occured : {}", exception.getMessage());
    ErrorLog error =
        new ErrorLog(exception.getClass().getSimpleName(), exception.getMessage(), payload);
    errorLogRepository.save(error);
    log.info("error log info saved : {}", payload);
  }

}
