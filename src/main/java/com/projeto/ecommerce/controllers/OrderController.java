package com.projeto.ecommerce.controllers;

import com.projeto.ecommerce.requests.OrderRequestDTO;
import com.projeto.ecommerce.requests.UserRequestDTO;
import com.projeto.ecommerce.responses.OrderResponseDTO;
import com.projeto.ecommerce.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){this.orderService = orderService;}

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderReq){
        OrderResponseDTO orderRes = orderService.createOrder(orderReq);
        return ResponseEntity.ok("produto criado com sucesso!");
    }
}
