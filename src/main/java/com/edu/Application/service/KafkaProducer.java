package com.edu.Application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaProducer {

    @Value("${topic.eloApplication}")
    private String topic;

    private KafkaTemplate<String, String> kafkaTemplate;

    private KafkaProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String msg){
        log.info("send message to kafka");
        kafkaTemplate.send(topic, msg);
    }
}
