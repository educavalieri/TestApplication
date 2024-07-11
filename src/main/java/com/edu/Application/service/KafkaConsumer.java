package com.edu.Application.service;

import com.edu.Application.entity.OrderEntity;
import com.edu.Application.enums.StatusEnum;
import com.edu.Application.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "topic_eloApplication_kafka", groupId = "group_id")
    public void receiveMessage(String msg){
      log.info("Receive message from Kafka");
      try {
          orderRepository.save(new OrderEntity(msg, StatusEnum.ENVIADO_TRANSPORTADORA));
      }catch (Exception e){
          e.printStackTrace();
      }
      log.info("Order processed with success");
    }

}
