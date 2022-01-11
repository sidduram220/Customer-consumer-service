package com.customer.consumer.service.kafka.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.customer.consumer.service.model.CustomerRequest;

@Configuration
@EnableKafka
public class ConsumerConfig {

  @Bean
  public ConsumerFactory<String, CustomerRequest> consumerFactory() {
    JsonDeserializer<CustomerRequest> deserializer = new JsonDeserializer<>(CustomerRequest.class);
    deserializer.setRemoveTypeHeaders(false);
    deserializer.addTrustedPackages("*");
    deserializer.setUseTypeMapperForKey(true);
    Map<String, Object> configs = new HashMap<>();
    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        "127.1.0.0:9092");
    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class);
    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        deserializer);
    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "siddu1");
    return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), deserializer);

  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, CustomerRequest> concurrentKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, CustomerRequest> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;

  }
}
