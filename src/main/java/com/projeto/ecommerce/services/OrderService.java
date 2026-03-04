package com.projeto.ecommerce.services;

import com.projeto.ecommerce.entities.OrderEntity;
import com.projeto.ecommerce.entities.UserEntity;
import com.projeto.ecommerce.enums.StatusDoPedido;
import com.projeto.ecommerce.repositories.OrderRepository;
import com.projeto.ecommerce.repositories.UserRepository;
import com.projeto.ecommerce.requests.OrderRequestDTO;
import com.projeto.ecommerce.requests.UserRequestDTO;
import com.projeto.ecommerce.responses.OrderResponseDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public OrderResponseDTO createOrder(UUID id, OrderRequestDTO orderReq, UserRequestDTO userReq) {
        if (orderReq == null || userReq == null) {
            throw new IllegalArgumentException("os dados inseridos são inválidos!");
        }
        if (orderRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("esse pedido já existe");
        }
        OrderEntity newOrder = new OrderEntity();
        newOrder.setStatus(StatusDoPedido.AWAITING_PAYMENT);
        newOrder.setMoment(LocalDateTime.now());

        UserEntity client = userRepository.findById(orderReq.getClient())
                .orElseThrow(() -> new IllegalArgumentException("usuário não encontrado"));

        newOrder.setClient(client);

        OrderEntity savedOrder = orderRepository.save(newOrder);

        return new OrderResponseDTO(savedOrder.getClient(), savedOrder.getStatus(), savedOrder.getMoment());
    }

}
