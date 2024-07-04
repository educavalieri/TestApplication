package com.edu.eloApplication.service.Implements;

import com.edu.eloApplication.entity.OrderEntity;
import com.edu.eloApplication.enums.StatusEnum;
import com.edu.eloApplication.repository.OrderRepository;
//import com.edu.eloApplication.service.KafkaConsumer;
import com.edu.eloApplication.service.KafkaProducer;
import com.edu.eloApplication.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

//    @Autowired
//    private KafkaConsumer kafkaConsumer;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public void create(String productName) {
        try {
            orderRepository.save(new OrderEntity(productName, StatusEnum.AGUARDANDO_ENVIO));
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("Save information on Mongo with success");
        kafkaProducer.sendMessage(productName);
    }

    @Override
    public OrderEntity consult(String productName) {
        return orderRepository.findById(productName).get();
    }

}
