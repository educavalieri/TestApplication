package com.edu.eloApplication.repository;

import com.edu.eloApplication.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {
}
