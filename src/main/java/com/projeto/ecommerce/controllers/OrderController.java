package com.projeto.ecommerce.controllers;

import com.projeto.ecommerce.enums.StatusDoPedido;
import com.projeto.ecommerce.requests.OrderRequestDTO;
import com.projeto.ecommerce.requests.UserRequestDTO;
import com.projeto.ecommerce.responses.OrderResponseDTO;
import com.projeto.ecommerce.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // criar pedido
    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderReq) {
        OrderResponseDTO orderRes = orderService.createOrder(orderReq);
        return ResponseEntity.ok(orderRes);
    }

    // Mostrar pedido por ID
    @GetMapping("/show/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable UUID id) {
        OrderResponseDTO orderRes = orderService.getOrderById(id);
        return ResponseEntity.ok(orderRes);
    }

}
