package com.customer.consumer.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {

  static Logger log = LoggerFactory.getLogger(ObjectMapperUtil.class);


  private ObjectMapperUtil() {
    log.info("Utility class");
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (JsonProcessingException exception) {
      log.info("Object mapper exception : " + exception.getMessage());
    }
    return null;
  }
}
