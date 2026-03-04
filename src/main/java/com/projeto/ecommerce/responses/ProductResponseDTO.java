package com.projeto.ecommerce.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private String name;
    private String description;
    private Double price;


    public ProductResponseDTO(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
