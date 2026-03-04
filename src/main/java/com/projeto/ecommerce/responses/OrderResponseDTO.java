package com.projeto.ecommerce.responses;

import com.projeto.ecommerce.entities.UserEntity;
import com.projeto.ecommerce.enums.StatusDoPedido;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class OrderResponseDTO {
    private UUID id;
    private UserEntity client;
    private LocalDateTime moment;
    private StatusDoPedido status;

    public OrderResponseDTO(UserEntity client, StatusDoPedido status, LocalDateTime moment) {
        this.client = client;
        this.status = status;
        this.moment = moment;
    }
}
