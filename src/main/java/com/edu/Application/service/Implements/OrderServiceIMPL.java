package com.edu.Application.service.Implements;

import com.edu.Application.entity.OrderEntity;
import com.edu.Application.entity.dto.OrderDTO;
import com.edu.Application.enums.StatusEnum;
import com.edu.Application.repository.OrderRepository;
import com.edu.Application.service.KafkaProducer;
import com.edu.Application.service.exceptions.ResourceNotFoundException;
import com.edu.Application.service.interfaces.OrderService;
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
