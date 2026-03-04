package com.projeto.ecommerce.entities;

import com.projeto.ecommerce.enums.StatusDoPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDateTime moment;
    private StatusDoPedido status;
//  Anotation pra falar que é uma relação de muitos pra 1
    @ManyToOne
//  define qual coluna será usada como chave estrangeira na tabela
    @JoinColumn(name  = "cliente_id")
    private UserEntity client;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaymentEntity payment;

    public void setClient(UserEntity client) {
        this.client = client;
    }
}
