package com.edu.eloApplication.controller;

import com.edu.eloApplication.entity.OrderEntity;
import com.edu.eloApplication.service.Implements.OrderServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderServiceIMPL orderServiceIMPL;

    @GetMapping(value = "/id/{productId}")
    public ResponseEntity<OrderEntity> consult(@PathVariable("productId") String productId) {
        return ResponseEntity.ok().body(orderServiceIMPL.consult(productId));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody String productName){
        orderServiceIMPL.create(productName);
        return ResponseEntity.ok().build();
    }
}