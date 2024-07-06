package com.edu.eloApplication.controller;

import com.edu.eloApplication.entity.OrderEntity;
import com.edu.eloApplication.entity.dto.OrderDTO;
import com.edu.eloApplication.service.Implements.OrderServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderServiceIMPL orderServiceIMPL;

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<OrderEntity> consult(@PathVariable("id") String productId) {
        return ResponseEntity.ok().body(orderServiceIMPL.consult(productId));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody OrderDTO orderDTO){
        orderServiceIMPL.create(orderDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/process")
    public ResponseEntity process(@RequestBody OrderDTO orderDTO){
        orderServiceIMPL.process(orderDTO);
        return ResponseEntity.ok().build();
    }
}