package com.edu.eloApplication.service;

import com.edu.eloApplication.entity.OrderEntity;
import com.edu.eloApplication.enums.StatusEnum;
import com.edu.eloApplication.repository.OrderRepository;
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
