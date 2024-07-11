package com.edu.Application.service.interfaces;

import com.edu.Application.entity.OrderEntity;
import com.edu.Application.entity.dto.OrderDTO;

public interface OrderService {

    void create(OrderDTO orderDTO);

    OrderEntity consult(String id);

    void process(OrderDTO orderDTO);

}
