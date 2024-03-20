package com.venkatesh.kafkaproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

// To create topic form springboot itself than cli
@Configuration
public class KafkaProducerConfig {
    @Bean
    public NewTopic createTopic(){
        return new NewTopic("kafka-demo2",3,(short) 1);
    }
    @Bean
    public Map<String,Object> produceConfig(){
        Map<String,Object> pro=new HashMap<>();
        pro.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        pro.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        pro.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return pro;
    }
    @Bean
    public ProducerFactory<String,Object> producerFactory(){
        return new DefaultKafkaProducerFactory<>(produceConfig());
    }
    @Bean
    public KafkaTemplate<String,Object> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
