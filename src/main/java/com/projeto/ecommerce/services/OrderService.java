package com.projeto.ecommerce.services;

import com.projeto.ecommerce.entities.OrderEntity;
import com.projeto.ecommerce.entities.OrderItemEntity;
import com.projeto.ecommerce.entities.ProductEntity;
import com.projeto.ecommerce.entities.UserEntity;
import com.projeto.ecommerce.enums.StatusDoPedido;
import com.projeto.ecommerce.repositories.OrderItemRepository;
import com.projeto.ecommerce.repositories.OrderRepository;
import com.projeto.ecommerce.repositories.ProductRepository;
import com.projeto.ecommerce.repositories.UserRepository;
import com.projeto.ecommerce.requests.OrderItemRequestDTO;
import com.projeto.ecommerce.requests.OrderRequestDTO;
import com.projeto.ecommerce.requests.UserRequestDTO;
import com.projeto.ecommerce.responses.OrderItemResponseDTO;
import com.projeto.ecommerce.responses.OrderResponseDTO;
import com.projeto.ecommerce.responses.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private ProductRepository productRepository;
    private OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO orderReq) {
        if (orderReq == null) {
            throw new IllegalArgumentException("os dados inseridos são inválidos!");
        }
//        Criado o pedido
        OrderEntity newOrder = new OrderEntity();
        newOrder.setStatus(StatusDoPedido.AWAITING_PAYMENT);
        newOrder.setMoment(LocalDateTime.now());
//      Buscando o cliente
        UserEntity client = userRepository.findById(orderReq.getClient())
                .orElseThrow(() -> new IllegalArgumentException("usuário não encontrado"));

        newOrder.setClient(client);
//      salvando o pedido
        OrderEntity savedOrder = orderRepository.save(newOrder);
//      salvando os items
        List<OrderItemResponseDTO> orderResponse = new ArrayList<>();
        for (OrderItemRequestDTO itemReq : orderReq.getItems()) {
            ProductEntity product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("produto não encontrado"));

            OrderItemEntity orderItem = new OrderItemEntity(savedOrder, product, itemReq.getQuantity(), product.getPrice());
            orderItemRepository.save(orderItem);
//            fazer o metodo de lista de items
//            itemResponses.add(new OrderItemResponseDTO(product.getId(), product.getName(), itemReq.getQuantity(), product.getPrice()));
        }


        UserResponseDTO dto = new UserResponseDTO(client);
        return new OrderResponseDTO(savedOrder.getId(),dto, savedOrder.getStatus(), savedOrder.getMoment());
    }

}
