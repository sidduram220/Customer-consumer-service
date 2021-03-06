package com.customer.consumer.service.kafka.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.customer.consumer.service.model.Customer;

@Configuration
@EnableKafka
public class ConsumerConfig {

  @Value("${bootstrap.server}")
  private String bootstrapServer;

  @Value("${group.id}")
  private String groupId;

  @Bean
  public ConsumerFactory<String, Customer> consumerFactory() {
    JsonDeserializer<Customer> deserializer = new JsonDeserializer<>(Customer.class);
    deserializer.setRemoveTypeHeaders(false);
    deserializer.addTrustedPackages("*");
    deserializer.setUseTypeMapperForKey(true);
    Map<String, Object> configs = new HashMap<>();
    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        bootstrapServer);
    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class);
    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        deserializer);
    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, groupId);
    return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), deserializer);

  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Customer> concurrentKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Customer> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;

  }
}
