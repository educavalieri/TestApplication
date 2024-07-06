package com.edu.eloApplication.service.Implements;

import com.edu.eloApplication.entity.OrderEntity;
import com.edu.eloApplication.entity.dto.OrderDTO;
import com.edu.eloApplication.enums.StatusEnum;
import com.edu.eloApplication.repository.OrderRepository;
import com.edu.eloApplication.service.KafkaProducer;
import com.edu.eloApplication.service.exceptions.ResourceNotFoundException;
import com.edu.eloApplication.service.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public void create(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity(orderDTO.getId(), StatusEnum.AGUARDANDO_ENVIO);
        try {
            orderRepository.save(orderEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("Save information on Mongo with success");
    }

    @Override
    public OrderEntity consult(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This id was not found"));
    }

    @Override
    public void process(OrderDTO orderDTO) {
        OrderEntity entity = consult(orderDTO.getId());
        kafkaProducer.sendMessage(orderDTO.getId());

    }

}
