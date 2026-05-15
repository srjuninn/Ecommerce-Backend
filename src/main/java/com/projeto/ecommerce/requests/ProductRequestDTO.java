package com.projeto.ecommerce.requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ProductRequestDTO {
    @NotBlank
    private String name;
    private String description;
    @NotNull
    @Positive
    private Double price;
    private String photo;

    public ProductRequestDTO(String name, String description, Double price, String photo) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.photo = photo;
    }
}
