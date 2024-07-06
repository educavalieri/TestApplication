package com.edu.eloApplication.service;

import com.edu.eloApplication.entity.OrderEntity;
import com.edu.eloApplication.entity.dto.OrderDTO;

public interface OrderService {

    void create(OrderDTO orderDTO);

    OrderEntity consult(String id);

}
