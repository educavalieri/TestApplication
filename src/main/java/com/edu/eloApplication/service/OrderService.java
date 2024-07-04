package com.edu.eloApplication.service;

import com.edu.eloApplication.entity.OrderEntity;

public interface OrderService {

    void create(String productName);

    OrderEntity consult(String productName);

}
