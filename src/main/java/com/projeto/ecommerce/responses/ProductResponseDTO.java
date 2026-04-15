package com.projeto.ecommerce.responses;

import com.projeto.ecommerce.entities.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductResponseDTO {
    UUID id;
    private String name;
    private String description;
    private Double price;


    public ProductResponseDTO(UUID id ,String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductResponseDTO(ProductEntity product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}
